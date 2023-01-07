package telran.spring.calculator.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;


@RestController
@RequestMapping("calculator")
public class CalculatorController {
	
	@Value("${app.message.wrong.operation: wrong operation - }")
	String wrongOperationMessage;
	List<Operation> operationsList;
	private Map<String, Operation> operations;

	public CalculatorController(List<Operation> operationsList) {
		this.operationsList = operationsList;
	}

	@PostMapping
	private String calculate(@RequestBody @Valid OperationData data) {
		Operation operation = operations.get(data.operationName);
		return operation != null ? operation.execute(data) : wrongOperationMessage + data.operationName;
	}
	
	@GetMapping
	private Map<String, Set<String>> getOperations() {
		return operations.keySet().stream().collect(Collectors.toMap(Function.identity(), k -> {
			 Map<String, ? extends Object> methods = operations.get(k).getMethods();
					if(methods == null) {
					return Set.of("no methods available");	
					}
					return methods.keySet();
		}));
	}
	
	@PostConstruct
	void displayTypes() {
		operations = operationsList.stream().collect(Collectors.toMap(Operation::getOperationName, Function.identity()));
		System.out.printf("application context is created with types %s%n", operations.keySet());
	}

	@PreDestroy
	void shutdown() {
		System.out.println("Bye, performed graceful shutdown");
	}

}
