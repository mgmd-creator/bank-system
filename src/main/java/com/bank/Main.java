package com.bank;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.service.BankService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== БАНКОВСКАЯ СИСТЕМА ===");
            System.out.println("1. Создать дебетовый счет");
            System.out.println("2. Создать кредитный счет");
            System.out.println("3. Создать депозитный счет");
            System.out.println("4. Пополнить счет");
            System.out.println("5. Снять средства");
            System.out.println("6. Перевести средства между счетами");
            System.out.println("7. Проверить баланс счета");
            System.out.println("8. Показать список всех счетов");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт меню: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1: {
                        System.out.print("Введите ID счета: ");
                        String id = scanner.next();
                        System.out.print("Введите начальный баланс: ");
                        double balance = scanner.nextDouble();
                        bankService.createDebitAccount(id, balance);
                        System.out.println("Дебетовый счет успешно создан!");
                        break;
                    }
                    case 2: {
                        System.out.print("Введите ID счета: ");
                        String id = scanner.next();
                        System.out.print("Введите начальный баланс: ");
                        double balance = scanner.nextDouble();
                        System.out.print("Введите кредитный лимит: ");
                        double limit = scanner.nextDouble();
                        bankService.createCreditAccount(id, balance, limit);
                        System.out.println("Кредитный счет успешно создан!");
                        break;
                    }
                    case 3: {
                        System.out.print("Введите ID депозитного счета: ");
                        String id = scanner.next();
                        System.out.print("Введите начальный баланс: ");
                        double balance = scanner.nextDouble();
                        bankService.createDepositAccount(id, balance); // Теперь создается именно DepositAccount!
                        System.out.println("Депозитный счет успешно создан!");
                        break;
                    }
                    case 4: {
                        System.out.print("Введите ID счета: ");
                        String id = scanner.next();
                        System.out.print("Введите сумму пополнения: ");
                        double amount = scanner.nextDouble();
                        bankService.deposit(id, amount);
                        System.out.println("Счет успешно пополнен!");
                        break;
                    }
                    case 5: {
                        System.out.print("Введите ID счета: ");
                        String id = scanner.next();
                        System.out.print("Введите сумму для снятия: ");
                        double amount = scanner.nextDouble();
                        bankService.withdraw(id, amount);
                        System.out.println("Средства успешно сняты!");
                        break;
                    }
                    case 6: {
                        System.out.print("Введите ID счета отправителя: ");
                        String from = scanner.next();
                        System.out.print("Введите ID счета получателя: ");
                        String to = scanner.next();
                        System.out.print("Введите сумму перевода: ");
                        double amount = scanner.nextDouble();
                        bankService.transfer(from, to, amount);
                        System.out.println("Перевод выполнен успешно!");
                        break;
                    }
                    case 7: {
                        System.out.print("Введите ID счета: ");
                        String id = scanner.next();
                        Account acc = bankService.findAccount(id);
                        System.out.println("Баланс: " + acc.getBalance() + " (Активен: " + acc.isActive() + ")");
                        break;
                    }
                    case 8: {
                        java.util.List<Account> allAccounts = bankService.getAllAccounts();
                        if (allAccounts.isEmpty()) {
                            System.out.println("В банке пока нет открытых счетов.");
                        } else {
                            System.out.println("\n=== СПИСОК ВСЕХ СЧЕТОВ ===");
                            for (Account acc : allAccounts) {
                                System.out.println("ID: " + acc.getId() +
                                        ", Баланс: " + acc.getBalance() +
                                        ", Активен: " + acc.isActive() +
                                        ", Тип: " + acc.getClass().getSimpleName());
                            }
                        }
                        break;
                    }
                    case 0: {
                        System.out.println("Выход из программы.");
                        return;
                    }
                    default:
                        System.out.println("Неверный пункт меню.");
                }
            } catch (BankException | IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}