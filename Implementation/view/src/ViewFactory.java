public class ViewFactory
{
  private ViewModelFactory viewModelFactory;
  private ViewHandler viewHandler;
  private AddRemoveBookViewController bookController;
  private AddRemoveMagazineViewController magazineController;


  public ViewFactory(ViewHandler viewHandler,ViewModelFactory viewModelFactory)
  {
    this.viewHandler=viewHandler;
    this.viewModelFactory=viewModelFactory;
    bookController=null;
    magazineController=null;
  }
}
