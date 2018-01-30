package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Jogo.Dias;
import com.example.service.StorageService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private StorageService storageService;

	@LocalServerPort
	private int port;

	@Test
	public void shouldUploadFile() throws Exception {
		ClassPathResource resource = new ClassPathResource("testupload.txt", getClass());

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // você pode usar outras máscaras
		Date y = new Date();
		System.out.println(sdf1.format(y));

		GregorianCalendar gc = new GregorianCalendar();
	//	System.out.println(gc.isSet(gc.TUESDAY)); // Mostra qual o dia da semana 1 = domingo, 2=segunda, etc

		//	gc.add(gc.DATE, shouldDownloadFile2(Dias.DOMINGO,gc));
			System.out.println("Hora Inicial : " +sdf1.format(shouldDownloadFile2(Dias.DOMINGO,gc,"12:30").getTime()));
			gc = new GregorianCalendar();
			System.out.println("Hora Inicial : " +sdf1.format(shouldDownloadFile2(Dias.DOMINGO,gc,"13:30").getTime()));

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("file", resource);
		ResponseEntity<String> response = this.restTemplate.postForEntity("/upload", map, String.class);

		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.FOUND);
		assertThat(response.getHeaders().getLocation().toString())
				.startsWith("http://localhost:" + this.port + "/upload");
		then(storageService).should().store(any(MultipartFile.class));
	}

	@Test
	public void shouldDownloadFile() throws Exception {
		ClassPathResource resource = new ClassPathResource("testupload.txt", getClass());
		given(this.storageService.loadAsResource("testupload.txt")).willReturn(resource);

		ResponseEntity<String> response = this.restTemplate.getForEntity("/files/{filename}", String.class,
				"testupload.txt");

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION))
				.isEqualTo("attachment; filename=\"testupload.txt\"");
		assertThat(response.getBody()).isEqualTo("Spring Framework");
	}
	
	public GregorianCalendar shouldDownloadFile2(Dias dia,GregorianCalendar gc,String hInc)
	{
		Integer a= 0;
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.SUNDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 0;
				break;
			case SEGUNDA:
				a = 1;
				break;
			case TERCA:
				a = 2;
				break;
			case QUARTA:
				a = 3;
				break;
			case QUINTA:
				a = 4;
				break;
			case SEXTA:
				a = 5;
				break;
			case SABADO:
				a = 6;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.MONDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 6;
				break;
			case SEGUNDA:
				a = 0;
				break;
			case TERCA:
				a = 1;
				break;
			case QUARTA:
				a = 2;
				break;
			case QUINTA:
				a = 3;
				break;
			case SEXTA:
				a = 4;
				break;
			case SABADO:
				a = 5;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.TUESDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 5;
				break;
			case SEGUNDA:
				a = 6;
				break;
			case TERCA:
				a = 0;
				break;
			case QUARTA:
				a = 1;
				break;
			case QUINTA:
				a = 2;
				break;
			case SEXTA:
				a = 3;
				break;
			case SABADO:
				a = 4;
				break;
			}
		}else

		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.WEDNESDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 4;
				break;
			case SEGUNDA:
				a = 5;
				break;
			case TERCA:
				a = 6;
				break;
			case QUARTA:
				a = 0;
				break;
			case QUINTA:
				a = 1;
				break;
			case SEXTA:
				a = 2;
				break;
			case SABADO:
				a = 3;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.THURSDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 3;
				break;
			case SEGUNDA:
				a = 4;
				break;
			case TERCA:
				a = 5;
				break;
			case QUARTA:
				a = 6;
				break;
			case QUINTA:
				a = 0;
				break;
			case SEXTA:
				a = 1;
				break;
			case SABADO:
				a = 2;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.FRIDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 2;
				break;
			case SEGUNDA:
				a = 3;
				break;
			case TERCA:
				a = 4;
				break;
			case QUARTA:
				a = 5;
				break;
			case QUINTA:
				a = 6;
				break;
			case SEXTA:
				a = 1;
				break;
			case SABADO:
				a = 2;
				break;
			}
		}else
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == gc.SATURDAY) {
			switch (dia) { 
			case DOMINGO:
					a = 1;
				break;
			case SEGUNDA:
				a = 2;
				break;
			case TERCA:
				a = 3;
				break;
			case QUARTA:
				a = 4;
				break;
			case QUINTA:
				a = 5;
				break;
			case SEXTA:
				a = 6;
				break;
			case SABADO:
				a = 7;
				break;
			}
		}
		
		gc.add(gc.DATE, a);
		String[] rabbitmqUserInfo = hInc.split(":");
		gc.set(GregorianCalendar.HOUR_OF_DAY,Integer.parseInt(rabbitmqUserInfo[0]));
		gc.set(GregorianCalendar.MINUTE,Integer.parseInt(rabbitmqUserInfo[1]));
		
		//gc.add(gc.HOUR, Integer.parseInt(rabbitmqUserInfo[0]));
	//	gc.add(gc.MINUTE, Integer.parseInt(rabbitmqUserInfo[0]));
		return gc;
	}

}
