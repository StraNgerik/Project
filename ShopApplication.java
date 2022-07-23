import java.util.Scanner;

public class ShopApplication
{
    public static class User
    {
        public int ID;
        public  String FirstName;
        public  String LastName;
        public int MoneyAmount;
        User(int ID,String Fname,String LName,int MAmount)
        {
            this.ID=ID;
            this.MoneyAmount=MAmount;
            this.FirstName=Fname;
            this.LastName=LName;
        }
    }


    public static class Product
    {
        public int ID;
        public String Name;
        public int Price;
        public Product(int ID,String Name,int Price)
        {
            this.ID=ID;
            this.Name=Name;
            this.Price=Price;
        }
    }

    public static class Log
    {
        public int UserID;
        public int ProductID;
        public Log(int UID,int PID)
        {
            this.ProductID=PID;
            this.UserID=UID;
        }
    }
    public User[] UBase = new User[3];
    public Product[] PBase = new Product[3];
    public Log[] Logs = new Log[100];
    public void DisplayAllUsers(User[] UBase)
    {
        for(int i=0;i<UBase.length;i++)
        {
            System.out.println("First Name = "+UBase[i].LastName+" Last Name = "+UBase[i].FirstName+" ID = "+UBase[i].ID+" Money Amount = "+UBase[i].MoneyAmount);
        }
    }
    public void DisplayAllProducts(Product[] PBase)
    {
        for(int i=0;i<PBase.length;i++)
        {
            System.out.println("Name = "+PBase[i].Name+" ID = "+PBase[i].ID+" Price = "+PBase[i].Price);
        }
    }
    public void Buying(User[] UBase,Product[] PBase,Log[] LBase,int RealLogLen,int UserID,int ProductID)
    {
        int PArrID=0;
        int UArrID=0;
        for(int i=0;i<UBase.length;i++)
            if(UBase[i].ID==UserID)
            {
                UArrID=i;
                break;
            }
        for(int i=0;i<PBase.length;i++)
            if(PBase[i].ID==UserID)
            {
                PArrID=i;
                break;
            }
        if(UBase[UArrID].MoneyAmount<PBase[PArrID].Price)System.out.println("Error,Not enough money to buy");
        else
        {
            UBase[UArrID].MoneyAmount-=PBase[PArrID].Price;
            LBase[RealLogLen].ProductID=ProductID;
            LBase[RealLogLen].UserID=UserID;
            System.out.println("Successful purchase");
        }
    }
    public void DisplayAllUserBoughts(User[] UBase,Product[] PBase,Log[] LBase,int RealLogLen,int UserID)
    {
        for(int j=0;j<UBase.length;j++)
            if(UBase[j].ID==UserID)
            {
                System.out.println("Name of buyer = "+UBase[j].LastName+" "+UBase[j].FirstName);
                break;
            }
        for(int i=0;i<RealLogLen;i++)
            if(LBase[i].UserID==UserID)
            {
                for(int j=0;j<PBase.length;j++)
                    if(LBase[i].ProductID==PBase[j].ID)
                    {
                        System.out.println("  Name of product = " + PBase[j].Name);
                        break;
                    }
            }
    }
    public void DisplayAllProductBoughts(User[] UBase,Product[] PBase,Log[] LBase,int RealLogLen,int ProductID)
    {
        for(int j=0;j<PBase.length;j++)
            if(LBase[j].ProductID==ProductID)
            {
                System.out.println("Name of product = " + PBase[j].Name);
                break;
            }
        for(int i=0;i<RealLogLen;i++)
            if(LBase[i].ProductID==ProductID)
            {
                for(int j=0;j<UBase.length;j++)
                    if(UBase[j].ID==LBase[j].UserID)
                    {
                        System.out.println("Name of buyer = "+UBase[j].LastName+" "+UBase[j].FirstName);
                        break;
                    }
            }
    }

    public static void main(String[] args)
    {
        ShopApplication a = new ShopApplication();
        a.UBase[0] = new User(228,"Sergiy","Pritula",500);
        a.UBase[1] = new User(1337,"Petro","Poroshenko",0);
        a.UBase[2] = new User(226,"Vasya","Pupkin",200);
        a.PBase[0] = new Product(121,"Phone",200);
        a.PBase[1] = new Product(122,"Nokia",100);
        a.PBase[2] = new Product(123,"Ruzzia",500);
        for(int i=0;i<100;i++)
        {
            a.Logs[i] = new Log(0,0);
        }
        Scanner scanner = new Scanner(System.in);
        int RealLogLen=0;
        int choise;
        boolean mainloop=true;
        while (mainloop)
        {
            System.out.println("Please choose mode. 1 - Display All Users. 2 - Display All Products. 3 - Buy. 4 - Display all Products of User. 5 - Display all Users that bought product. 6 - Exit.");
            choise = scanner.nextInt();
            switch (choise) {
                case 1:
                    a.DisplayAllUsers(a.UBase);
                    System.out.println();
                    break;
                case 2:
                    a.DisplayAllProducts(a.PBase);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Please write UserID and ProductID");
                    a.Buying(a.UBase,a.PBase,a.Logs, RealLogLen, scanner.nextInt(), scanner.nextInt());
                    RealLogLen++;
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Please write UserID");
                    a.DisplayAllUserBoughts(a.UBase,a.PBase,a.Logs, RealLogLen, scanner.nextInt());
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Please write ProductID");
                    a.DisplayAllProductBoughts(a.UBase,a.PBase,a.Logs, RealLogLen, scanner.nextInt());
                    System.out.println();
                    break;
                case 6:
                    mainloop=false;
                    break;
                default:
                    System.out.println("Wrong number! Do again");
                    break;
            }
        }
    }

}

