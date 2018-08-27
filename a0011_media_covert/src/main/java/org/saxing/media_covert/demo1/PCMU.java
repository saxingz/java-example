package org.saxing.media_covert.demo1;


public class PCMU extends BaseG711 {

    static final int _u2a[] = { 					/* u- to A-law conversions */
            1,		1,		2,		2,		3,		3,		4,		4,
            5,		5,		6,		6,		7,		7,		8,		8,
            9,		10, 	11, 	12, 	13, 	14, 	15, 	16,
            17, 	18, 	19, 	20, 	21, 	22, 	23, 	24,
            25, 	27, 	29, 	31, 	33, 	34, 	35, 	36,
            37, 	38, 	39, 	40, 	41, 	42, 	43, 	44,
            46, 	48, 	49, 	50, 	51, 	52, 	53, 	54,
            55, 	56, 	57, 	58, 	59, 	60, 	61, 	62,
            64, 	65, 	66, 	67, 	68, 	69, 	70, 	71,
            72, 	73, 	74, 	75, 	76, 	77, 	78, 	79,
            81, 	82, 	83, 	84, 	85, 	86, 	87, 	88,
            89, 	90, 	91, 	92, 	93, 	94, 	95, 	96,
            97, 	98, 	99, 	100,	101,	102,	103,	104,
            105,	106,	107,	108,	109,	110,	111,	112,
            113,	114,	115,	116,	117,	118,	119,	120,
            121,	122,	123,	124,	125,	126,	127,	128};


    static final int _a2u[] = { 					/* A- to u-law conversions */
            1,		3,		5,		7,		9,		11, 	13, 	15,
            16, 	17, 	18, 	19, 	20, 	21, 	22, 	23,
            24, 	25, 	26, 	27, 	28, 	29, 	30, 	31,
            32, 	32, 	33, 	33, 	34, 	34, 	35, 	35,
            36, 	37, 	38, 	39, 	40, 	41, 	42, 	43,
            44, 	45, 	46, 	47, 	48, 	48, 	49, 	49,
            50, 	51, 	52, 	53, 	54, 	55, 	56, 	57,
            58, 	59, 	60, 	61, 	62, 	63, 	64, 	64,
            65, 	66, 	67, 	68, 	69, 	70, 	71, 	72,
            73, 	74, 	75, 	76, 	77, 	78, 	79, 	80,
            80, 	81, 	82, 	83, 	84, 	85, 	86, 	87,
            88, 	89, 	90, 	91, 	92, 	93, 	94, 	95,
            96, 	97, 	98, 	99, 	100,	101,	102,	103,
            104,	105,	106,	107,	108,	109,	110,	111,
            112,	113,	114,	115,	116,	117,	118,	119,
            120,	121,	122,	123,	124,	125,	126,	127};


    protected static int alaw2ulaw(int aval)
    {  aval&=0xff;
        return ((aval & 0x80)!=0)? (0xFF^_a2u[aval^0xD5]) : (0x7F^_a2u[aval^0x55]);
    }

    protected static int ulaw2alaw(int uval)
    {  uval&=0xff;
        return ((uval&0x80)!=0)? (0xD5^(_u2a[0xFF^uval]-1)) : (0x55^(_u2a[0x7F^uval]-1));
    }

    public static void ulaw2linear(byte ulaw[],short lin[],int frames) {
        int i;
        for (i = 0; i < frames; i++) {
            lin[i] = a2s[ulaw2alaw(ulaw[i] & 0xff)];
        }
    }
    public static void linear2ulaw(short lin[],int offset,byte ulaw[],int frames) {
        int i;
        for (i = 0; i < frames; i++) {
            ulaw[i] = (byte)alaw2ulaw(s2a[lin[i+offset] & 0xffff]);
        }
    }

    public static byte[] ulaw2linear(byte ulaw[],int frames) {
        int i;
        byte[] retArr = new byte[frames*2];
        short linTmp ;
        int retArrPos = 0;

        for (i = 0; i < frames; i++)
        {
            linTmp = a2s[ulaw2alaw(ulaw[i] & 0xff)];
            retArrPos = i*2;
            retArr[retArrPos] = (byte)(linTmp & 0xff);
            retArr[retArrPos+1] = (byte)((linTmp >> 8) & 0xff);
        }

        return retArr;
    }
    //change end
}
