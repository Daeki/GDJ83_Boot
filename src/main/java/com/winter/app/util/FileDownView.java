package com.winter.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.qna.QnaFileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FileDownView extends AbstractView {
	@Value("${app.upload}")
	private String path;//D:/upload/
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QnaFileVO qnaFileVO = (QnaFileVO) model.get("file");
		String directory = (String) model.get("board");

		// 1. 폴더 경로 준비
		String path = this.path+directory;//D:/upload/qna

		// 2. 파일 준비
		File file = new File(path, qnaFileVO.getFileName());

		// 3. 응답시 인코딩 처리(Filter로 처리 했으면 선택)
		response.setCharacterEncoding("UTF-8");

		// 4. 파일의 크기 지정
		response.setContentLength((int) file.length());

		// 5. 다운로드시 파일이름지정, 인코딩 설정
		String name = qnaFileVO.getOriName();
		name = URLEncoder.encode(name, "UTF-8");

		// 6. Header 정보 설정
		response.setHeader("Content-Disposition", "attachment;fileName=\"" + name + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

		// 7. Client 전송
		// HDD에서 파일을 읽어와서 Client로 output
		FileInputStream fi = new FileInputStream(file);
		OutputStream os = response.getOutputStream();

		FileCopyUtils.copy(fi, os);

		// 8. 해제
		os.close();
		fi.close();
		
	}

}
