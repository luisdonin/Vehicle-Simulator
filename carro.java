import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class carro {
    public static void main(String[] args) {
        CarroEsportivo carroEsportivo = new CarroEsportivo();
        CarroSedan carroSedan = new CarroSedan();
        JFrame frame = new JFrame("Carros");
        JPanel panel = new JPanel();
        JTextField textField = new JTextField(10);
        JButton button = new JButton("OK");
        panel.add(textField);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        button.addActionListener(e -> {
            int opcao = Integer.parseInt(textField.getText());
            switch (opcao) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Carro Esportivo selecionado.");
                    while (true) {
                        int acao = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma ação:\n1 - Andar\n2 - Abastecer\n3 - Arrumar freios\n0 - Voltar"));
                        if (acao == 0) {
                            break;
                        }
                        switch (acao) {
                            case 1:
                                double distancia = Double.parseDouble(JOptionPane.showInputDialog("Digite a distância em Km: "));
                                double velocidade = Double.parseDouble(JOptionPane.showInputDialog("Digite a velocidade em Km/h: "));
                                carroEsportivo.andar(distancia, velocidade);
                                break;
                            case 2:
                                double quantidade = Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de combustível em litros: "));
                                String tipo = JOptionPane.showInputDialog("Digite o tipo de combustível (Gasolina, Álcool ou Diesel): ");
                                carroEsportivo.abastecer(quantidade, tipo);
                                break;
                            case 3:
                                carroEsportivo.arrumarFreios();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opção inválida.");
                                break;
                        }
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Carro Sedan selecionado.");
                    while (true) {
                        int acao = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma ação:\n1 - Andar\n2 - Abastecer\n3 - Arrumar freios\n0 - Voltar"));
                        if (acao == 0) {
                            break;
                        }
                        switch (acao) {
                            case 1:
                                double distancia = Double.parseDouble(JOptionPane.showInputDialog("Digite a distância em Km: "));
                                double velocidade = Double.parseDouble(JOptionPane.showInputDialog("Digite a velocidade em Km/h: "));
                                carroSedan.andar(distancia, velocidade);
                                break;
                            case 2:
                                double quantidade = Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de combustível em litros: "));
                                String tipo = JOptionPane.showInputDialog("Digite o tipo de combustível (Gasolina, Álcool ou Diesel): ");
                                carroSedan.abastecer(quantidade, tipo);
                                break;
                            case 3:
                                carroSedan.arrumarFreios();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opção inválida.");
                                break;
                        }
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        });
    }
}

class Carro {
    protected double quilometragem;
    protected double nivelCombustivel;
    protected String tipoCombustivel;
    protected boolean freiosOk;

    public Carro() {
        this.quilometragem = 0;
        this.nivelCombustivel = 1;
        this.tipoCombustivel = "Gasolina";
        this.freiosOk = true;
    }

    public void andar(double distancia, double velocidade) {
        if (!freiosOk) {
            JOptionPane.showMessageDialog(null, "Os freios precisam ser arrumados antes de andar.");
            return;
        }

        double consumo = 0;
        if (this instanceof CarroEsportivo) {
            if (velocidade < 120) {
                consumo = distancia / 10;
            } else {
                consumo = distancia / 5;
            }
        } else if (this instanceof CarroSedan) {
            if (velocidade < 100) {
                consumo = distancia / 25;
            } else {
                consumo = distancia / 10;
                if (velocidade > 99) {
                    consumo *= 2;
                }
            }
        }

        if (consumo > nivelCombustivel) {
            JOptionPane.showMessageDialog(null, "Não há combustível suficiente para andar essa distância.");
            return;
        }

        quilometragem += distancia;
        nivelCombustivel -= consumo;
        JOptionPane.showMessageDialog(null, "Andou " + distancia + " Km a " + velocidade + " Km/h.\nConsumo: " + consumo + " litros.\nQuilometragem total: " + quilometragem + " Km.\nNível de combustível: " + nivelCombustivel + " litros.");
    }

    public void abastecer(double quantidade, String tipo) {
        if (!tipoCombustivel.equals(tipo)) {
            nivelCombustivel = 0;
            tipoCombustivel = tipo;
        }

        if (tipo.equals("Gasolina") && nivelCombustivel + quantidade > 80) {
            JOptionPane.showMessageDialog(null, "O tanque não comporta essa quantidade de gasolina.");
            return;
        } else if (tipo.equals("Álcool") && nivelCombustivel + quantidade > 80) {
            JOptionPane.showMessageDialog(null, "O tanque não comporta essa quantidade de álcool.");
            return;
        } else if (tipo.equals("Diesel") && nivelCombustivel + quantidade > 80) {
            JOptionPane.showMessageDialog(null, "O tanque não comporta essa quantidade de diesel.");
            return;
        }

        nivelCombustivel += quantidade;
        JOptionPane.showMessageDialog(null, "Abasteceu " + quantidade + " litros de " + tipo + ".\nNível de combustível: " + nivelCombustivel + " litros.");
    }

    public void arrumarFreios() {
        if (this instanceof CarroEsportivo) {
            JOptionPane.showMessageDialog(null, "O carro esportivo não precisa arrumar os freios.");
        } else if (this instanceof CarroSedan) {
            freiosOk = true;
            JOptionPane.showMessageDialog(null, "Os freios foram arrumados.");
        }
    }
}

class CarroEsportivo extends Carro {
    public CarroEsportivo() {
        super();
    }
}

class CarroSedan extends Carro {
    private int vidaUtilFreios;

    public CarroSedan() {
        super();
        this.vidaUtilFreios = 1000;
    }

    @Override
    public void andar(double distancia, double velocidade) {
        super.andar(distancia, velocidade);
        vidaUtilFreios -= distancia;
        if (vidaUtilFreios <= 0) {
            freiosOk = false;
            JOptionPane.showMessageDialog(null, "Os freios precisam ser arrumados.");
        }
    }

    @Override
    public void arrumarFreios() {
        vidaUtilFreios = 1000;
        freiosOk = true;
        JOptionPane.showMessageDialog(null, "Os freios foram arrumados.");
    }
}