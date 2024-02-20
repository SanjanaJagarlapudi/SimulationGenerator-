package mvc;

/* Class "AppFactory" Datalog
4/4/2023 - Sanjana Jagarlapudi: Created File and method headers
4/6/2023 - Sanjana Jagarlapudi: Edited method defintions

 */

public interface AppFactory {
    public Model makeModel();
    public View makeView(Model model);
    public String[] getEditCommands();
    public Command makeEditCommand(Model model, String type, Object source);
    public String getTitle();
    public String[] getHelp();
    public String about();
}
