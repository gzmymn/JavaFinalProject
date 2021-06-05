package kodlamaio.hrms.configurations;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfiguration {

	@Bean
    public Cloudinary cloudinaryService(){
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name","gizemymn",
                        "api_key", "287771524376457",
                        "api_secret","KU4O5Mz4E0iR4DJFcCdCkJLzPZw"
                )
        );
    }
}
