package reybo.authentication.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reybo.authentication.entities.Captcha;

@Repository
public interface CaptchaRepository extends MongoRepository<Captcha, String> {
}