package com.unsubble.web.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import com.unsubble.backend.model.User;
import com.unsubble.web.entities.UserAdapter;

public class AdminController {
	private static final AdminController INSTANCE = new AdminController();
	private Map<String, UserAdapter> table;
	private Timer timer;

	private AdminController() {
		table = new ConcurrentHashMap<String, UserAdapter>();
		timer = new Timer();
	}

	public Optional<UserAdapter> getAdmin(String username) {
		if (username == null)
			return Optional.empty();
		if (!table.containsKey(username)) {
			User user = UserRepositoryController.getInstance().getUser(username);
			putTableIfExists(username, new UserAdapter(user));
		}
		return Optional.ofNullable(table.getOrDefault(username, null));
	}

	private void putTableIfExists(String username, UserAdapter user) {
		if (!checkPrivelegedUsers(username)) {
			return;
		}
		timer.purge();
		if (table.isEmpty()) {
			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					for (Map.Entry<String, UserAdapter> entry : table.entrySet()) {
						if (entry.getValue().decrementTime() == 0)
							removeFromTable(username);
					}
				}
			}, 0L, 1000L);
		}
		table.put(username, user);	
	}
	
	private boolean checkPrivelegedUsers(String username) {
		Path p = Path.of("/home/oem/Desktop/projects/SupportAndTicketingSystem/privilegedUserList.txt");
		if (Files.notExists(p))
			return false;
		try {
			return Files.lines(p).filter(username::equalsIgnoreCase).findAny().isPresent();
		} catch (IOException e) {
			return false;
		}
	}

	private void removeFromTable(String username) {
		table.remove(username);
		if (table.isEmpty()) {
			timer.cancel();
		}
	}
	
	public boolean isAdmin(String username) {
		return getAdmin(username).isPresent();
	}

	public static AdminController getInstance() {
		return INSTANCE;
	}

}