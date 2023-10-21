import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static void main(String[] args) {
        CarroEsportivo carroEsportivo = new CarroEsportivo();
        CarroSedan carroSedan = new CarroSedan();
        JFrame frame = new JFrame("Escolha um carro? (1=Esportivo|2=Sedan|3=Sair)");
        JPanel panel = new JPanel();
        JTextField textField = new JTextField(40);
        JButton button = new JButton("OK");
        panel.add(textField);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

       
        frame.getRootPane().setDefaultButton(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcao = Integer.parseInt(textField.getText());
                switch (opcao) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Carro Esportivo selecionado.");
                        while (true) {
                            int acao = Integer.parseInt(JOptionPane.showInputDialog("(Escolha uma acao? 1 - Andar | 2 - abastecer | 3 - arrumar freios | 0 - sair)"));
                            if (acao == 0) {
                                System.exit(0);
                                break;
                            }
                            switch (acao) {
                                case 1:
                                    double distancia = Double.parseDouble(JOptionPane.showInputDialog("distancia em km: "));
                                    double velocidade = Double.parseDouble(JOptionPane.showInputDialog("velocidade em km/h: "));
                                    carroEsportivo.andar(distancia, velocidade);
                                    break;
                                case 2:
                                    double quantidade = Double.parseDouble(JOptionPane.showInputDialog("quantidade de combustivel em litros: "));
                                    String tipo = JOptionPane.showInputDialog("tipo de combustivel (gasolina, alcool ou diesel): ");
                                    carroEsportivo.abastecer(quantidade, tipo);
                                    break;
                                case 3:
                                    carroEsportivo.arrumarFreios();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "opaco invalida.");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Carro Sedan selecionado.");
                        while (true) {
                            int acao = Integer.parseInt(JOptionPane.showInputDialog("(Escolha uma acao? 1 - Andar | 2 - abastecer | 3 - arrumar freios | 0 - sair)"));
                            if (acao == 0) {
                                System.exit(0);
                                break;
                            }
                            switch (acao) {
                                case 1:
                                    double distancia = Double.parseDouble(JOptionPane.showInputDialog("distancia em km: "));
                                    double velocidade = Double.parseDouble(JOptionPane.showInputDialog("velocidade em km/h: "));
                                    carroSedan.andar(distancia, velocidade);
                                    break;
                                case 2:
                                    double quantidade = Double.parseDouble(JOptionPane.showInputDialog("quantidade de combustivel em litros: "));
                                    String tipo = JOptionPane.showInputDialog("tipo de combustivel (gasolina, alcool ou diesel): ");
                                    carroSedan.abastecer(quantidade, tipo);
                                    break;
                                case 3:
                                    carroSedan.arrumarFreios();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "opaco invalida");
                                    break;
                            }
                        }
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "opaco invalida");
                        break;
                }
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
        this.tipoCombustivel = "gasolina";
        this.freiosOk = true;
    }

    public void andar(double distancia, double velocidade) {
        if (!freiosOk) {
            JOptionPane.showMessageDialog(null, "Os freios precisam ser arrumados antes de andar");
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
            JOptionPane.showMessageDialog(null, "combustivel insuficiente");
            return;
        }

        quilometragem += distancia;
        nivelCombustivel -= consumo;
        JOptionPane.showMessageDialog(null, "rodou " + distancia + " km a " + velocidade + " km/h.\nConsumo: " + consumo + " litros.\nkm total: " + quilometragem + " km.\ncombustivel no tanque: " + nivelCombustivel + " litros");
    }

    public void abastecer(double quantidade, String tipo) {
        if (!tipoCombustivel.equals(tipo)) {
            nivelCombustivel = 0;
            tipoCombustivel = tipo;
        }

        if (tipo.equals("gasolina") && nivelCombustivel + quantidade > 80) {
            JOptionPane.showMessageDialog(null, "o tanque nao comporta essa quantidade de gasolina");
            return;
        } else if (tipo.equals("alcool") && nivelCombustivel + quantidade > 80) {
            JOptionPane.showMessageDialog(null, "o tanque nao comporta essa quantidade de alcool");
            return;
        } else if (tipo.equals("diesel") && nivelCombustivel + quantidade > 80) {
            JOptionPane.showMessageDialog(null, "o tanque nao comporta essa quantidade de diesel");
            return;
        }

        nivelCombustivel += quantidade;
        JOptionPane.showMessageDialog(null, "abasteceu " + quantidade + " litros de " + tipo + ".\nNível de combustivel: " + nivelCombustivel + " litros.");
    }

    public void arrumarFreios() {
        if (this instanceof CarroEsportivo) {
            JOptionPane.showMessageDialog(null, "Os freios foram arrumados");
        } else if (this instanceof CarroSedan) {
            freiosOk = true;
            JOptionPane.showMessageDialog(null, "Os freios foram arrumados");
        }
    }
}

class CarroEsportivo extends Carro {
    private int vidaUtilFreios;
    public CarroEsportivo() {
        super();
        this.vidaUtilFreios = 1000;
    }

    @Override
    public void andar(double distancia, double velocidade) {
        super.andar(distancia, velocidade);
        vidaUtilFreios -= distancia;
        if (vidaUtilFreios <= 0) {
            freiosOk = false;
            JOptionPane.showMessageDialog(null, "os freios precisam ser arrumados");
        }
    }

    @Override
    public void arrumarFreios() {
        vidaUtilFreios = 1000;
        freiosOk = true;
        JOptionPane.showMessageDialog(null, "os freios foram arrumados");
    }
    
}

class CarroSedan extends Carro {
    private int vidaUtilFreios;

    public CarroSedan() {
        super();
        this.vidaUtilFreios = 100;
    }

    @Override
    public void andar(double distancia, double velocidade) {
        super.andar(distancia, velocidade);
        vidaUtilFreios -= distancia;
        if (vidaUtilFreios <= 0) {
            freiosOk = false;
            JOptionPane.showMessageDialog(null, "Os freios precisam ser arrumados");
        }
    }

    @Override
    public void arrumarFreios() {
        vidaUtilFreios = 100;
        freiosOk = true;
        JOptionPane.showMessageDialog(null, "Os freios foram arrumados");
    }
}