//package model;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//
//public class LibraryUserList implements Serializable {
//    private final ArrayList<LibraryUser> users;
//
//    public LibraryUserList(){
//        users = new ArrayList<>();
//    }
//
//    public void addUserList(LibraryUser libraryUser){
//        users.add(libraryUser);
//    }
//
//    public LibraryUser getUser(int ssn){
//        return users.get(ssn);
//    }
//
//    public int getSize(){
//        return users.size();
//    }
//
//    @Override public String toString(){
//        String list="";
//        for (int i = 0; i < users.size(); i++) {
//            list+=users.get(i)+ "";
//        }
//        return list.trim();
//    }
//
//}