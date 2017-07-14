/**
 * Created by 江婷婷 on 2017/7/13.
 */
class Student
{
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private String email;

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
    public int getAge()
    {
        return this.age;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
    public String getGender()
    {
        return this.gender;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getPhone()
    {
        return this.phone;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return this.address;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }

    public Student() {}
    public Student(String name,int age,String gender,String phone,String address,String email)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public void eat()
    {
        System.out.println("吃");
    }
    public void drink()
    {
        System.out.println("喝");
    }
    public void play()
    {
        System.out.println("玩");
    }
    public void sleep()
    {
        System.out.println("睡");
    }
}


public class studentTest
{
    public static void main(String args[])
    {
        Student stu1 = new Student("张三",18,"男","123","das","1@1.com");
        Student stu2 = new Student("李四",18,"女","13","sa","1@12.com");
        Student[] date = {stu1,stu2};
        String[] find = {"张三","1@1.com","das"};
        int flag = 0;
        for (int i = 0; i < 2; i++)
        {
            if(date[i].getName().equals(find[0]) && date[i].getEmail().equals(find[1]) && date[i].getAddress().equals(find[2]))
            {
                System.out.println("已找到");
                flag = 1;
                break;
            }
        }
        if(flag == 0)
        {
            System.out.println("未找到");
        }
    }

}
