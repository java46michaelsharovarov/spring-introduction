package telran.spring.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

import telran.spring.service.Sender;
import telran.spring.view.Item;

@Component
public class MessageSenderMenu {

	private Map<String, Sender> senders;

	public MessageSenderMenu(Map<String, Sender> senders) {
		this.senders = senders;
	}

	public ArrayList<Item> getMessageSenderItems() {
		ArrayList<Item> menuItems = new ArrayList<>();
		senders.entrySet().stream().forEach(sender -> 
			menuItems.add(Item.of(sender.getKey(), io -> 
				sender.getValue().send(io, io.readString("Enter text")))));
		menuItems.add(Item.exit());
		return menuItems;
	}
	
	public Item[] getMessageSenderOptions() {
		Item [] items = {
				Item.of("Send message", io -> {
					String messageType = io.readOption("Enter message type " + senders.keySet().toString(),
							"Wrong message type", senders.keySet().stream().toList());
					Sender sender = senders.get(messageType);
					sender.send(io, io.readString("Enter text"));
				}),
				Item.exit()
		};
		return items;
	 }

}
