package view;

import viewModel.AddRemoveLibraryUserViewModel;

import javax.swing.plaf.synth.Region;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class AddRemoveLibraryUserViewController
{
    private ViewHandler viewHandler;
    private Region root;
    private AddRemoveLibraryUserViewModel viewModel;


    public void init(ViewHandler viewHandler, AddRemoveLibraryUserViewModel viewModel, Region root) throws SQLException, RemoteException{
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }
}
