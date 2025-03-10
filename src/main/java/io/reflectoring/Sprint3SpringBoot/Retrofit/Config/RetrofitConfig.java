package io.reflectoring.Sprint3SpringBoot.Retrofit.Config;

/*
import io.reflectoring.Sprint3SpringBoot.Retrofit.Controllers.TourController;
*/
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reflectoring.Sprint3SpringBoot.Retrofit.IService.*;
/*
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.TourService;
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Configuration class for setting up Retrofit clients with Jackson converter.
 * This class provides Retrofit instances for different API services.
 */
@Configuration
public class RetrofitConfig {

    private static final String BASE_URL_DEVICE = "http://localhost:5269/api/";
    private static final String BASE_URL_CONTINUOUSUSEDEVICE = "http://localhost:5269/api/";
    private static final String BASE_URL_FOUNTAIN = "http://localhost:5269/api/";
    private static final String BASE_URL_WATERANALYSIS = "http://localhost:5269/api/";


    /**
     * Creates and configures a Retrofit client for interacting with the device API.
     *
     * @return an instance of {@link IDeviceService} for API calls.
     */
    @Bean
    public IDeviceService deviceApiClient(ObjectMapper objectMapper) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_DEVICE)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        return retrofit.create(IDeviceService.class);
    }

    /**
     * Creates and configures a Retrofit client for interacting with the continuous use device API.
     *
     * @return an instance of {@link IContinuousUseDeviceService} for API calls.
     */
    @Bean
    public IContinuousUseDeviceService continuousUseDeviceApiClient(ObjectMapper objectMapper) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_CONTINUOUSUSEDEVICE)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        return retrofit.create(IContinuousUseDeviceService.class);
    }

    /**
     * Creates and configures a Retrofit client for interacting with the fountain API.
     *
     * @return an instance of {@link IFountainService} for API calls.
     */
    @Bean
    public IFountainService fountainApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_FOUNTAIN)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(IFountainService.class);
    }

    /**
     * Creates and configures a Retrofit client for interacting with the water analysis API.
     *
     * @return an instance of {@link IWaterAnalysisService} for API calls.
     */
    @Bean
    public IWaterAnalysisService waterAnalysisApiClient(ObjectMapper objectMapper) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_WATERANALYSIS)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        return retrofit.create(IWaterAnalysisService.class);
    }
}
