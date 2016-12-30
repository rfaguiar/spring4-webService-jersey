package test;
import java.io.File;

import com.google.api.services.storage.model.StorageObject;

import br.com.livro.util.CloudStorageUtil;
import br.com.livro.util.Constants;

public class CloudStorageUploadTest {

	public static void main(String[] args) throws Exception {
		CloudStorageUtil c = new CloudStorageUtil(Constants.BUCKET_NAME);
		
		File p12File = new File(Constants.P12_FILE);
		
		// Conecta
		c.connect(Constants.ACCOUNT_ID, p12File);
		// Upload
		System.out.println("Fazendo upload...");
		File file = new File("Ferrari_FF.png");
		String contentType = "image/png";
		String storageProjectId = Constants.PROJECT_ID;
		StorageObject obj = c.upload(Constants.BUCKET_NAME, file, contentType,
				storageProjectId);
		System.out.println("Upload OK, objeto: " + obj.getName());
	}
}
