import java.util.Arrays;

/**
 * Created by 江婷婷 on 2017/7/11.
 */

public class Num2Rmb {
    private String[] hanArr = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    private String[] unitArr = {"十","百","千","万","亿"};

    /**
     *一个分离小数的方法
     * @param num
     * @return一个字符串数组 两个元素 第一个元素是整数部分字符串 第二个是小数部分
     */

    private String[] divide(double num)
    {
        long zheng = (long)num;
        long xiao = Math.round((num-zheng)*100);//四舍五入
        return new String[]{zheng+"",String.valueOf(xiao)};
    }


    private String xiaoqian(String numStr)
    {
        String result = "";


        if (numStr.charAt(numStr.length()-1) - 48 != 0) result += hanArr[numStr.charAt(numStr.length()-1)-48] + "分";
        if (numStr.length()>1 && (numStr.charAt(0) - 48 != 0)) result = hanArr[numStr.charAt(0)-48] + "角" + result;
        return result;

    }
    /**
     *
     * @param numStr
     * @return
     */
    private String toHanStr(String numStr)
    {
        String result ="";
        int numLen = numStr.length();

        int flag=0;
        int nflag=1;

        for(int i=numLen-1,count=0,n=0;i>=0;i--)
        {
            int num=numStr.charAt(i)-48;

            if(n!=0 && flag!=n)
            {
                int cuflag=0;
                for(int k=i,j=0;k>=0&&j<4;k--,j++)
                {
                    if((numStr.charAt(k)-48)!=0)
                    {
                        //    System.out.println(k);
                        cuflag=1;
                        break;
                    }
                }

                if(cuflag==1)
                {
                    result = unitArr[n + 2] + result;
                    flag = n;
                    nflag = 0;
                }
            }

            if(num!=0)
            {
                if (count == 0)
                {
                    result = hanArr[num] + result;
                }
                else
                {
                    result = hanArr[num] + unitArr[count - 1] + result;
                }
                nflag=1;
            }
            else
            {
                if(count!=0 && i!=0 && nflag!=0)
                {

                    int cuflag=0;
                    for(int k=i;k>=0;k--)
                    {
                        if((numStr.charAt(k)-48)!=0)
                        {
                            //    System.out.println(k);
                            cuflag=1;
                            break;
                        }
                    }

                    if(cuflag==1)
                    {
                        result = hanArr[0] + result;
                        nflag = 0;
                    }
                }
            }

            count++;
            if(count==4)
            {
                count=0;
                n++;
            }

        }
        return result;
    }

    public static void main(String[] args)
    {
        Num2Rmb nr = new Num2Rmb();
        String[] number = nr.divide(206071125.115);
        System.out.println(nr.toHanStr(number[0])+"元"+nr.xiaoqian(number[1]));
    }

}

