package Registration;


import com.isa.user.User;

import java.util.ArrayList;
import java.util.List;

    public class Registration {
        private List<User> users;

        public Registration() {
            users = new ArrayList<>();
        }

        public boolean isUserExists(String login) {
            for (User user : users) {
                if (user.getLogin().equals(login)) {
                    return true;
                }
            }
            return false;
        }

        public void registerUser(String firstName, String lastName, String login, String password) {
            if (isUserExists(login)) {
                System.out.println("Użytkownik istnieje.");
            } else {
                User newUser = new User(login, password, "", false);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                users.add(newUser);
                System.out.println("Konto zostało utworzone.");
                System.out.println("Automatycznie przejście do opcji logowania...");
            }
        }

        public static void main(String[] args) {
            Registration registration = new Registration();
            String firstName = "Jan";
            String lastName = "Kowalski";
            String login = "Jan.kowalski";
            String password = "Password123 !";
            registration.registerUser(firstName, lastName, login, password);
        }
    }

