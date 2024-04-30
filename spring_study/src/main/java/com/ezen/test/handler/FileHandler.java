package com.ezen.test.handler;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.test.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component

public class FileHandler {
	
	// 실제 파일이 저장되는 경로
	private final String UP_DIR="/Users/kimnoa/Desktop/noa/_myProject/_java";

	public List<FileVO> uploadFiles(MultipartFile[] files) {
		// 리턴 객체 생성
		List<FileVO> flist = new ArrayList<FileVO>();
		
		//MultipartFile[] 받아서 FileVO 형태의 List로 생성후 리턴
		// 오늘 날짜로 경로변경(가변형태로 저장) 년 월 일 폴더를 구성
		
		// 오늘날짜 경로생성
		LocalDate date = LocalDate.now();
		String today = date.toString();
		log.info(">>>> today>>>>{}",today);
		
		// 오늘날짜를 폴더형식으로 구성
		today = today.replace("-", File.separator);
		///Users/kimnoa/Desktop/noa/_myProject/_java
		
		File folders = new File(UP_DIR,today);
		// 폴더생성 => mkdir(폴더 한개생성), mkdirs(하위 폴더까지 구조로 생성)
		//exists : 있는지 없는지 확인
		if(!folders.exists()) {
			folders.mkdirs(); //폴더생성 명령
		}
		
		//flist설정
		
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today);
			fvo.setFile_size(file.getSize()); //return long
			
			// getOriginalFilename() : 경로+파일명 / 파일경로를 포함하는 케이스도 있음
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFile_name(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			String uuidStr = uuid.toString();
			fvo.setUuid(uuidStr);
			
			// 여기까지가 fvo 세팅완료------
			//bno,file_type
			
			//디스크에 저장
			// 디스크에 저장할 파일객체 생성 -> 저장
			//uuid_fileName / uuid_th_fileName
			
			String fullFileName = uuidStr +"_"+onlyFileName;
			File storeFile = new File(folders,fullFileName);
			
			// 저장 => 경로 또는 파일이 엇다면 IO 발생
			try {
				file.transferTo(storeFile);
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					
					// 썸네일생성
					File thumbNail = new File(folders,uuidStr+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
					
					
					
				}
				//파일타입 결정 -> 이미지만 썸네일 저장
			} catch (Exception e) {
				log.info(">>> 파일 저장 에러");
				e.printStackTrace();
			}
			
			flist.add(fvo);
		}
		
		
		return flist;
	}

	//tika를 활용한 파일형식 체크 ->이미지파일이 맞는지 확인
	public boolean isImageFile(File StoreFile) throws IOException{
		String mimeType = new Tika().detect(StoreFile);
		return mimeType.startsWith("image")? true:false;
	}
	
	
}
