package taye.kiosk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import taye.kiosk.domain.Menu;
import taye.kiosk.service.StoreService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KioskServerApplicationTests {

	@Autowired
	StoreService s;
		
    @LocalServerPort
    int port;

    TestRestTemplate testClient = new TestRestTemplate("1234", "1234");

    //@Test
    void test_1(){
        ResponseEntity<List<Menu>> resp = testClient.exchange("http://localhost:" + port + "/api/menu/favorite",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Menu>>() {
                });

        assertNotNull(resp.getBody());
        assertEquals(3, resp.getBody().size());
    }
}
