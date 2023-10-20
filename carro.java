import javax.swing.*;
import java.awt.event.*;

public class carro {
    
    public static class car {
        private String modelo;

        
        public car(String modelo) {
            this.modelo = modelo;
        }

       
        public String getmodelo() {
            return modelo;
        }

        public void setmodelo(String modelo) {
            this.modelo = modelo;
        }

        public String drive(int distance, int speed) {
            return "Você dirigiu seu carro " + modelo + " por " + distance + " quilometros em uma velocidade de " + speed + " km/h.";
        }

        public String fuel(String modeloCombustivel) {
            return "Você abasteceu seu carro " + modelo + " com " + modeloCombustivel + ".";
        }


        public double getConsumption() {
            return 0;
        }


        public double getFuel() {
            return 0;
        }


        public void setFuel(double d) {
        }
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Selecione o modelo de carro");
        frame.setSize(600, 500);
        
        JTextArea outputArea = new JTextArea(10, 30);
        
        JScrollPane scrollPane = new JScrollPane(outputArea); 
        frame.add(scrollPane); 
        
        frame.setVisible(true);
    
        while (true) {
            JTextField campoCarro = new JTextField(10);
            
            JDialog dialogCarro = new JDialog(frame, "Carro", true);
            dialogCarro.setLayout(new java.awt.FlowLayout());
            dialogCarro.add(new JLabel("Modelo de carro (1- esportivo | 2- sedan | 3- Sair):"));
            dialogCarro.add(campoCarro);
            
            JButton carBtn = new JButton("OK");
            carBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int modeloCarroSelecionado = Integer.parseInt(campoCarro.getText());
                    
                    if (modeloCarroSelecionado == 3) {
                        System.exit(0);
                    }
                    
                    String carmodelo = modeloCarroSelecionado == 1 ? "esportivo" : "sedan";
                    car myCar = new car(carmodelo);
                    
                    JTextField campoOpcao = new JTextField(10);
                    
                    JDialog dialogOpcao = new JDialog(frame, "Opcao", true);
                    dialogOpcao.setLayout(new java.awt.FlowLayout());
                    dialogOpcao.add(new JLabel("Escolha uma opcao (1- abastecer | 2- andar | 3- arrumar freios):"));
                    dialogOpcao.add(campoOpcao);
                    
                    JButton opcaoBtn = new JButton("OK");
                    opcaoBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int opcaoSelecionada = Integer.parseInt(campoOpcao.getText());
                            
                            if (opcaoSelecionada == 3) {
                                System.exit(0);
                            }
                            
                            switch(opcaoSelecionada) {
                                case 1:
                                    JTextField campoLitros = new JTextField(10);
    
                                    JDialog dialogLitros = new JDialog(frame, "Litros", true);
                                    dialogLitros.setLayout(new java.awt.FlowLayout());
                                    dialogLitros.add(new JLabel("Quantos litros?"));
                                    dialogLitros.add(campoLitros);
    
                                    JButton litrosBtn = new JButton("OK");
                                    litrosBtn.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            double litros = Double.parseDouble(campoLitros.getText());
                                            
                                            if (litros > 50) {
                                                JOptionPane.showMessageDialog(frame, "Tanque nao comporta essa quantidade de combustivel");
                                                dialogLitros.setVisible(false);
                                                dialogOpcao.setVisible(false);
                                                dialogCarro.setVisible(true);
                                            } else {
                                                JTextField campoCombustivel = new JTextField(10);
    
                                                JDialog dialogCombustiveDialog = new JDialog(frame, "Combustível", true);
                                                dialogCombustiveDialog.setLayout(new java.awt.FlowLayout());
                                                dialogCombustiveDialog.add(new JLabel("Combustível (1-Gasolina | 2- Etanol | 3- Diesel):"));
                                                dialogCombustiveDialog.add(campoCombustivel);
    
                                                JButton fuelButton = new JButton("OK");
                                                fuelButton.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent e) {
                                                        dialogCombustiveDialog.setVisible(false);
                                                        
                                                        dialogLitros.setVisible(false);
                                                        dialogOpcao.setVisible(false);
                                                        dialogCarro.setVisible(true);
                                                    }
                                                });
    
                                                campoCombustivel.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent e) {
                                                        fuelButton.doClick();
                                                    }
                                                });
    
                                                dialogCombustiveDialog.add(fuelButton);
    
                                                dialogCombustiveDialog.pack();
                                                dialogCombustiveDialog.setVisible(true);
    
                                                String modeloCombustivel;
                                                int combustivelSelecionado = Integer.parseInt(campoCombustivel.getText());
                                                switch(combustivelSelecionado) {
                                                    case 1:
                                                        modeloCombustivel = "gasolina";
                                                        break;
                                                    case 2:
                                                        modeloCombustivel = "etanol";
                                                        break;
                                                    case 3:
                                                        modeloCombustivel = "diesel";
                                                        break;
                                                    default:
                                                        throw new IllegalArgumentException("Combustivel invalido: " + combustivelSelecionado);
                                                }
                                                outputArea.append(myCar.fuel(modeloCombustivel) + "\n");
                                                
                                                myCar.setFuel(myCar.getFuel() + litros);
                                            }
                                        }
                                    });
    
                                    campoLitros.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            litrosBtn.doClick();
                                        }
                                    });
    
                                    dialogLitros.add(litrosBtn);
    
                                    dialogLitros.pack();
                                    dialogLitros.setVisible(true);
                                    break;
                                case 2:
                                    JTextField campoDistancia = new JTextField(10);
                                    JTextField campoVel = new JTextField(10);
    
                                    JDialog dirigeDialog = new JDialog(frame, "Dirigir", true);
                                    dirigeDialog.setLayout(new java.awt.FlowLayout());
                                    dirigeDialog.add(new JLabel("Distancia (km):"));
                                    dirigeDialog.add(campoDistancia);
                                    dirigeDialog.add(new JLabel("Velocidade (km/h):"));
                                    dirigeDialog.add(campoVel);
    
                                    JButton dirigeBtn = new JButton("Dirigir");
                                    dirigeBtn.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            int distancia = Integer.parseInt(campoDistancia.getText());
                                            int velocidade = Integer.parseInt(campoVel.getText());
    
                                            outputArea.append(myCar.drive(distancia, velocidade) + "\n");
    
                                            double fuelConsumed = (distancia / 100.0) * myCar.getConsumption() * (velocidade > 120 ? 1.5 : 1.0);
                                            myCar.setFuel(myCar.getFuel() - fuelConsumed);
    
                                            String fuelToAdd = JOptionPane.showInputDialog(frame, "Quantidade de combustível para adicionar (em litros):");
                                            double fuelToAddDouble = Double.parseDouble(fuelToAdd);
                                            myCar.setFuel(myCar.getFuel() + fuelToAddDouble);
    
                                            dirigeDialog.setVisible(false);
                                            
                                            dialogOpcao.setVisible(false);
                                            dialogCarro.setVisible(true);
                                        }
                                    });
    
                                    campoVel.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            dirigeBtn.doClick();
                                        }
                                    });
    
                                    dirigeDialog.add(dirigeBtn);
    
                                    dirigeDialog.pack();
                                    dirigeDialog.setVisible(true);
                                    break;
                                default:
                                    throw new IllegalArgumentException("Opcao invalida: " + opcaoSelecionada);
                            }
                            
                            dialogOpcao.setVisible(false);
                        }
                    });
                    
                    campoOpcao.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            opcaoBtn.doClick();
                        }
                    });
                    
                    dialogOpcao.add(opcaoBtn);
                    
                    dialogOpcao.pack();
                    dialogOpcao.setVisible(true);
                    
                    dialogCarro.setVisible(false);
                }
            });
            
            campoCarro.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carBtn.doClick();
                }
            });
            
            dialogCarro.add(carBtn);
            
            dialogCarro.pack();
            dialogCarro.setVisible(true);
        }
    }
}