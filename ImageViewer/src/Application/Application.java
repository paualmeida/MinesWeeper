package Application;

import Controller.Command;
import Controller.NextImageCommand;
import Controller.PrevImageCommand;
import Model.Image;
import View_Persistence.ListLoader;
import View_Persistence_File.FileImageListLoader;
import View_Swing.ActionListenerFactory;
import View_Swing.ApplicationFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    private Map<String, Command> commandMap;
    private ApplicationFrame frame;
    private String path;

    public static void main(String[] args) {
        new Application().execute("C:\\Users\\Public\\Pictures\\Sample Pictures");
    }

    private void execute(String path) {
        this.path = path;
        ListLoader loader = new FileImageListLoader(path);
        List<Image> album = loader.load();
        frame = new ApplicationFrame(createActionListenerFactory());
        frame.getViewer().setImage(album.get(0));
        createCommandMap();
    }

    private ActionListenerFactory createActionListenerFactory() {
        return new ActionListenerFactory() {
            @Override
            public ActionListener addAction(final String action) {
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Command command = commandMap.get(action);
                        if (command == null) {
                            return;
                        }
                        command.execute();
                    }
                };

            }
        };
    }

    private void createCommandMap() {
        commandMap = new HashMap<>();
        commandMap.put("Prev", new PrevImageCommand(frame.getViewer()));
        commandMap.put("Next", new NextImageCommand(frame.getViewer()));
    }
}