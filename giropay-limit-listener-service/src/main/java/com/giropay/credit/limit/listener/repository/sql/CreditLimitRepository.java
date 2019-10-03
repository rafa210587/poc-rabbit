package com.giropay.credit.limit.listener.repository.sql;

import com.giropay.credit.limit.listener.domain.entity.CreditLimitEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class CreditLimitRepository {

	private static List<CreditLimitEntity> LIMITS_MOCK = new ArrayList<>();

	public List<CreditLimitEntity> findAll() {
		return LIMITS_MOCK;
	}

	public List<CreditLimitEntity> findAllByIdClient(Integer idClient) {
		return LIMITS_MOCK
				.stream()
				.filter(l -> l.getIdClient().equals(idClient))
				.collect(Collectors.toList());
	}

	public List<CreditLimitEntity> findAllByIdClientAndIdStatusLimit(Integer idClient, Integer idStatusLimit) {
		List<CreditLimitEntity> limits = LIMITS_MOCK.stream()
				.filter(l -> l.getDateExclusion() == null 
						&& l.getIdClient().equals(idClient)
						&& l.getIdStatusLimit().equals(idStatusLimit))
				.collect(Collectors.toList());

		if (limits == null || limits.size() == 0) {
			throw new RuntimeException("Not found exception");
		}

		log.debug("found ".concat(String.valueOf(limits.size()))
				.concat(" to client id ").concat(String.valueOf(idClient)));

		return limits;
	}

	public CreditLimitEntity findById(Integer id) {
		Optional<CreditLimitEntity> entity = LIMITS_MOCK
				.stream()
				.filter(l -> l.getId().equals(id))
				.findAny();

		return entity.orElseThrow(() -> new RuntimeException("Not found exception"));
	}

	public void insertLimit(CreditLimitEntity entity) {
		LIMITS_MOCK.add(entity);
		log.info("new limit inserted to client id ".concat(String.valueOf(entity.getIdClient())));
	}

	public void updateLimitStatus(CreditLimitEntity entity, Integer idStatus) {
		LIMITS_MOCK.get(LIMITS_MOCK.indexOf(entity)).setIdStatusLimit(idStatus);

		StringBuilder msg = new StringBuilder();

		msg.append("limit with id ").append(entity.getId());
		msg.append(" now has status id ").append(idStatus);

		log.info(msg.toString());
	}

	public void updateLimitValue(CreditLimitEntity entity, Integer value) {
		LIMITS_MOCK.get(LIMITS_MOCK.indexOf(entity)).setValue(value);

		// atualizar no banco
		StringBuilder msg = new StringBuilder();

		msg.append("limit with id ").append(entity.getId());
		msg.append(" now has value ").append(value);
		msg.append(" of ").append(entity.getOriginalValue());

		log.info(msg.toString());
	}

	public void deleteLimitById(CreditLimitEntity entity) {
		LIMITS_MOCK.get(LIMITS_MOCK.indexOf(entity)).setDateExclusion(new Date());

		StringBuilder msg = new StringBuilder();

		msg.append("limit with id ").append(entity.getId());
		msg.append(" with client id ").append(entity.getIdClient());
		msg.append(" was excluded");

		log.info(msg.toString());
	}

}
