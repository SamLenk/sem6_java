import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Notebook> notebooks = new HashSet<>();

        Notebook notebook1 = new Notebook("Модель 1", 8, 256, "Windows 10", "Черный");
        Notebook notebook2 = new Notebook("Модель 2", 16, 512, "Windows 11", "Серебристый");
        Notebook notebook3 = new Notebook("Модель 3", 8, 512, "Windows 10", "Синий");
        Notebook notebook4 = new Notebook("Модель 4", 16, 1024, "MacOS", "Серый");
        notebooks.add(notebook1);
        notebooks.add(notebook2);
        notebooks.add(notebook3);
        notebooks.add(notebook4);

        try (Scanner scanner = new Scanner(System.in)) {
            Map<Integer, Object> criteria = new HashMap<>();
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");

            int criterion = scanner.nextInt();
            Object value;

            switch (criterion) {
                case 1:
                    System.out.println("Введите минимальное значение ОЗУ:");
                    value = scanner.nextInt();
                    criteria.put(1, value);
                    break;

                case 2:
                    System.out.println("Введите минимальный объем ЖД:");
                    value = scanner.nextInt();
                    criteria.put(2, value);
                    break;

                case 3:
                    System.out.println("Введите операционную систему:");
                    scanner.nextLine(); // Очистка буфера
                    value = scanner.nextLine();
                    criteria.put(3, value);
                    break;

                case 4:
                    System.out.println("Введите цвет:");
                    scanner.nextLine(); // Очистка буфера
                    value = scanner.nextLine();
                    criteria.put(4, value);
                    break;

                default:
                    System.out.println("Некорректный критерий.");
                    return;
            }

            filterNotebooks(notebooks, criteria);
        }
    }

    public static void filterNotebooks(Set<Notebook> notebooks, Map<Integer, Object> criteria) {
        for (Notebook notebook : notebooks) {
            boolean passFilter = true;
            for (Map.Entry<Integer, Object> entry : criteria.entrySet()) {
                int criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case 1:
                        if (notebook.getRam() < (int) value) {
                            passFilter = false;
                        }
                        break;

                    case 2:
                        if (notebook.getHdd() < (int) value) {
                            passFilter = false;
                        }
                        break;

                    case 3:
                        if (!notebook.getOs().equals(value)) {
                            passFilter = false;
                        }
                        break;

                    case 4:
                        if (!notebook.getColor().equals(value)) {
                            passFilter = false;
                        }
                        break;

                    default:
                        System.out.println("Некорректный критерий.");
                        return;
                }

                if (!passFilter) {
                    break;
                }
            }

            if (passFilter) {
                System.out.println(notebook);
            }
        }
    }
}

class Notebook {
    private String model;
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Notebook(String model, int ram, int hdd, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "model='" + model + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
