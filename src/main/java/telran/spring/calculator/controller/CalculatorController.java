package telran.spring.calculator.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
	
	Logger LOG = LoggerFactory.getLogger(CalculatorController.class);
	@Autowired
	ObjectMapper mapper;
	@Value("${app.message.wrong.operation: wrong operation - }")
	String wrongOperationMessage;
	List<Operation> operationsList;
	private Map<String, Operation> operations;

	public CalculatorController(List<Operation> operationsList) {
		this.operationsList = operationsList;
	}

	@PostMapping
	private String calculate(@RequestBody @Valid OperationData data) throws Exception {
		LOG.debug("RequestBody : {}", mapper.writeValueAsString(data));
		Operation operation = operations.get(data.operationName);
		if(operation == null) {
			LOG.error("ERROR: {}", wrongOperationMessage + data.operationName);
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, wrongOperationMessage + data.operationName);
			return String.format(wrongOperationMessage + data.operationName);
		}
		return operation.execute(data); 
	}
	
	@GetMapping
	private Map<String, Set<String>> getOperations() {
		return operations.keySet().stream().collect(Collectors.toMap(Function.identity(), k -> {
			  Set<String> methods = operations.get(k).getMethodNames();
					if(methods == null) {
					return Set.of("no methods available");	
					}
					return methods;
		}));
	}
	
	@PostConstruct
	void displayTypes() {
		operations = operationsList.stream().collect(Collectors.toMap(Operation::getOperationName, Function.identity()));
		LOG.info("application context is created with types {}", operations.keySet());
	}

	@PreDestroy
	void shutdown() {
		LOG.info("Bye, performed graceful shutdown");
	}

}
