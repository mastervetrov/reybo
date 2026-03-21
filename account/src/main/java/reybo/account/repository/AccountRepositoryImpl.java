package reybo.account.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reybo.account.dto.AccountSearchDto;
import reybo.account.entity.AccountEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepositoryImpl extends AccountRepository {


    default Page<AccountEntity> findByFilter(AccountSearchDto filter, Pageable pageable) {
        // Используем JPQL с параметрами
        return null; // Замени на реальную реализацию
    }


    default List<AccountEntity> findAllById(List<UUID> ids) {
        return null; // Замени на реальную реализацию
    }

    @Override
    default List<AccountEntity> findByQuery(String query) {
        return null; // Замени на реальную реализацию
    }
}
