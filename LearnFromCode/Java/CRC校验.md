# CRC校验

## 原理

生成一个CRC的流程为：

1. 预置一个16位寄存器为0FFFFH（全1），称之为CRC寄存器。

2. 把数据帧中的第一个字节的8位与CRC寄存器中的低字节进行异或运算，结果存回CRC寄存器。

3. 将CRC寄存器向右移一位，最高位填以0，最低位移出并检测。

4. 如果最低位为0：重复第三步（下一次移位）；如果最低位为1：将 CRC寄存器与一个预设的固定值（0A001H）进行异或运算。 

5. 重复第三步和第四步直到8次移位。这样处理完了一个完整的八位。

6. 重复第2步到第5步来处理下一个八位，直到所有的字节处理结束。

7. 最终CRC寄存器的值就是CRC的值。

## 实现

```Java

    public static byte[] crcCal(byte[] data, int start,int len){
        int crc16=0xffff;
        int pl = 0xa001;
        for (int i=start;i<start+len;i++){
            crc16^=(0x00ff & (int)data[i]);
            for (int j=0;j<8;j++){
                int temp = crc16 &0x0001;
                if (temp == 1){
                    crc16=(crc16>>1)^pl;
                }else {
                    crc16=crc16>>1;
                }
            }
        }
        byte high = (byte) ((crc16>>8)&0xff);
        byte low =(byte) (crc16 & 0xff);
        byte[] answer ={low,high};
        return answer;
    }
```
