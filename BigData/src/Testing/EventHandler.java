package Testing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    public void actionPerformed(ActionEvent arg0) {//�׼��̺�Ʈ�� �߻������� �����ϴ� ����
        System.out.println(arg0.getActionCommand());
    }
}