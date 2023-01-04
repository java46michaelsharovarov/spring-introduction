package telran.spring.calculator.controller;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@AllArgsConstructor
@RestController
@RequestMapping("calculator")
public class CalculatorController {

	private Map<String, Operation> operations;

	@PostMapping
	private String calculate(@RequestBody OperationData data) {
		Operation operation = operations.get(data.operationName);
		return operation != null ? operation.execute(data) : "Wrong operation - " + data.operationName;
	}
	
	@GetMapping
	private Map<String, Set<String>> getTypes() {
		return operations.keySet().stream().collect(Collectors.toMap(Function.identity(), k -> {
			 Map<String, Method> methods = operations.get(k).getMethods();
					if(methods == null) {
					return Set.of("no methods available");	
					}
					return methods.keySet();
		}));
	}

}
