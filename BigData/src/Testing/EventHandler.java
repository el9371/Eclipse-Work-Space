package Testing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    public void actionPerformed(ActionEvent arg0) {//액션이벤트가 발생됬을떄 수행하는 동작
        System.out.println(arg0.getActionCommand());
    }
}