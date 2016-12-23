package br.com.livro.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class UploadService {

	public String upload(String fileName, InputStream in) throws IOException{
		if(fileName == null && in == null){
			throw new IllegalArgumentException("Pârametros inválidos");
		}
		

		//Pasta temporaria da JVM
		File tmpDir = new File(System.getProperty("java.io.tmpdir"), "carros");
		if(!tmpDir.exists()){
			//cria a pasta carros se não existe
			tmpDir.mkdir();						
		}
		//cria arquivo
		File file = new File(tmpDir, fileName);
		FileOutputStream out = new FileOutputStream(file);
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(out);
		
		String path = file.getAbsolutePath();
		
		return path;		
		
	}
}
