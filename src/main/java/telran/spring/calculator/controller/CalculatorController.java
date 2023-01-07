package telran.spring.calculator.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
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
	private Set<String> getTypes() {
		return operations.keySet();
	}
	
	@PostConstruct
	void displayTypes() {
		operations = operationsList.stream().collect(Collectors.toMap(o -> getAnnotationValue(o), Function.identity()));
		System.out.printf("application context is created with types %s%n", operations.keySet());
	}
	
	private String getAnnotationValue(Operation o){
		Class<? extends Operation> clazz = o.getClass();
		String annotationVal = clazz.getDeclaredAnnotation(Service.class).value();
		if(annotationVal.isEmpty()) {
			throw new RuntimeException("no @Service annotation value");
//			return clazz.getSimpleName().replace("Operation", "");
		}
		return annotationVal;
	}

	@PreDestroy
	void shutdown() {
		System.out.println("Bye, performed graceful shutdown");
	}

}
