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
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Selecione o modelo de carro");
        frame.setSize(600, 500);
        
        JTextArea outputArea = new JTextArea(10, 30);
        
         JScrollPane scrollPane = new JScrollPane(outputArea); 
         frame.add(scrollPane); 
        
         frame.setVisible(true);

        JTextField campoCarro = new JTextField(10);
        
        JDialog dialogCarro = new JDialog(frame, "Carro", true);
        dialogCarro.setLayout(new java.awt.FlowLayout());
        dialogCarro.add(new JLabel("Modelo de carro (1- esportivo | 2- sedan | 3- Sair):"));
        dialogCarro.add(campoCarro);
        
        JButton carBtn = new JButton("OK");
        carBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogCarro.setVisible(false);
            }
        });
        
        dialogCarro.add(carBtn);
        
         dialogCarro.pack();
         dialogCarro.setVisible(true);

         JTextField campoCombustivel = new JTextField(10);
         
         JDialog dialogCombustiveDialog = new JDialog(frame, "Combustível", true);
         dialogCombustiveDialog.setLayout(new java.awt.FlowLayout());
         dialogCombustiveDialog.add(new JLabel("Combustível (1-Gasolina | 2- Etanol | 3- Diesel):"));
         dialogCombustiveDialog.add(campoCombustivel);
         
         JButton fuelButton = new JButton("OK");
         fuelButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 dialogCombustiveDialog.setVisible(false);
             }
         });
         
         dialogCombustiveDialog.add(fuelButton);
         
          dialogCombustiveDialog.pack();
          dialogCombustiveDialog.setVisible(true);

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
                  int modeloCarroSelecionado = Integer.parseInt(campoCarro.getText());
                  
                  if (modeloCarroSelecionado == 3) {
                      System.exit(0);
                  }
                  
                  String carmodelo = modeloCarroSelecionado == 1 ? "esportivo" : "sedan";
                  car myCar = new car(carmodelo);
                  
                  int combustivelSelecionado = Integer.parseInt(campoCombustivel.getText());
                  String modeloCombustivel;
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
                  
                  int distancia = Integer.parseInt(campoDistancia.getText());
                  int velocidade = Integer.parseInt(campoVel.getText());
                  
                  outputArea.append(myCar.drive(distancia, velocidade) + "\n");
                  
                  dirigeDialog.setVisible(false);
              }
          });
          
          dirigeDialog.add(dirigeBtn);
          
           dirigeDialog.pack();
           dirigeDialog.setVisible(true);
    }
}
