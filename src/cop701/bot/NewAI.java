package cop701.bot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cop701.common.GameState;
import cop701.common.Move;

public class NewAI extends AbstractAI {

	private final double weCapture[][] = { {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0011,1.0,0.0334,0.0337,0.0334,0.0346,1.0E-4,1.0E-4,0.0057,0.0054,0.0055,0.0054,0.0,0.0,8.0E-4,0.001,0.001,0.001,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,9.0E-4,0.0343,1.0,0.0671,0.0664,0.0688,0.0362,0.0086,0.0031,0.0125,0.0129,0.0126,0.0066,0.0021,9.0E-4,0.0024,0.0027,0.0019,0.0013,3.0E-4,3.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.001,0.0338,0.0684,0.1057,0.2672,0.2714,0.2403,0.2145,0.1842,0.0246,0.0502,0.0483,0.0428,0.0374,0.033,0.0054,0.0089,0.0087,0.0077,0.0071,0.0056,8.0E-4,5.0E-4,2.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.001,0.0348,0.0693,0.104,1.0,0.1087,0.0861,0.0654,0.0417,0.0471,0.0304,0.0409,0.0298,0.0222,0.0144,0.0146,0.0117,0.0107,0.0081,0.0063,0.0034,0.0035,0.0032,0.002,0.0012,0.001,5.0E-4,5.0E-4,2.0E-4,3.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,8.0E-4,0.0338,0.0703,0.1065,0.1176,1.0,0.1277,0.1058,0.0833,0.0859,0.0477,0.0295,0.0442,0.0349,0.0264,0.0252,0.0168,0.012,0.0124,0.0092,0.0071,0.0063,0.0042,0.0033,0.0024,0.0016,0.0013,9.0E-4,6.0E-4,6.0E-4,3.0E-4,3.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,7.0E-4,0.0281,0.0668,0.105,0.1167,0.1634,1.0,0.1449,0.1229,0.1256,0.0882,0.0509,0.034,0.0507,0.0399,0.0383,0.0286,0.019,0.0137,0.0149,0.0115,0.0095,0.0074,0.0052,0.0039,0.0031,0.0021,0.0019,0.0016,0.001,8.0E-4,7.0E-4,5.0E-4,3.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,2.0E-4,0.0066,0.0384,0.0785,0.0943,0.1434,0.1864,1.0,0.1605,0.1653,0.1275,0.0939,0.0569,0.0378,0.055,0.0515,0.0397,0.0313,0.022,0.0162,0.0177,0.0142,0.0112,0.0086,0.0061,0.0049,0.0037,0.0028,0.0023,0.0017,0.0013,0.0011,8.0E-4,6.0E-4,4.0E-4,3.0E-4,2.0E-4,2.0E-4,2.0E-4,1.0E-4,1.0E-4,0.0,0.0,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,2.0E-4,0.0055,0.0161,0.0514,0.0646,0.115,0.1635,0.2057,1.0,0.2048,0.1683,0.1372,0.1021,0.0636,0.0406,0.0659,0.0557,0.0444,0.0358,0.0268,0.0191,0.0211,0.0159,0.0137,0.0108,0.0078,0.0064,0.0049,0.0036,0.0028,0.0022,0.0018,0.0012,0.0011,9.0E-4,6.0E-4,5.0E-4,3.0E-4,3.0E-4,1.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,2.0E-4,0.0057,0.0145,0.027,0.0395,0.0866,0.1295,0.1693,0.2092,1.0,0.1932,0.1661,0.1367,0.102,0.0682,0.041,0.0668,0.0557,0.047,0.0383,0.0304,0.0204,0.0222,0.0178,0.0145,0.0121,0.0087,0.0074,0.0054,0.0051,0.0037,0.003,0.0024,0.002,0.0013,0.0012,0.001,5.0E-4,5.0E-4,4.0E-4,2.0E-4,3.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,1.0E-4,0.0,0.0,0.0,0.0},{0.0,1.0E-4,0.0053,0.0134,0.0254,0.0363,0.078,0.12,0.1655,0.2062,0.2511,1.0,0.2095,0.1807,0.1441,0.1084,0.0735,0.0458,0.0728,0.0611,0.051,0.042,0.0319,0.0222,0.0251,0.0198,0.0164,0.0135,0.01,0.008,0.007,0.0056,0.0039,0.0035,0.0027,0.0025,0.0019,0.0013,0.001,9.0E-4,6.0E-4,5.0E-4,4.0E-4,2.0E-4,2.0E-4,3.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0},{0.0,2.0E-4,0.0052,0.0129,0.0227,0.0325,0.0504,0.0882,0.1336,0.1801,0.2258,0.2637,0.2763,0.3923,0.3617,0.3287,0.2896,0.2524,0.0953,0.1074,0.0957,0.0838,0.0746,0.0647,0.0338,0.0332,0.0274,0.0233,0.019,0.0156,0.01,0.0077,0.006,0.0051,0.0044,0.0034,0.0027,0.0019,0.0017,0.0012,0.001,7.0E-4,8.0E-4,5.0E-4,3.0E-4,3.0E-4,4.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0},{0.0,1.0E-4,0.0043,0.0108,0.0202,0.0274,0.0427,0.0618,0.1013,0.1465,0.1951,0.2393,0.2766,1.0,0.2024,0.1763,0.1435,0.1086,0.1035,0.0753,0.0969,0.0813,0.0656,0.0511,0.0456,0.0355,0.0344,0.0284,0.0226,0.017,0.0141,0.0126,0.0103,0.008,0.0061,0.0054,0.0041,0.0032,0.0026,0.0018,0.0017,0.0012,9.0E-4,8.0E-4,6.0E-4,5.0E-4,3.0E-4,2.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4},{0.0,0.0,0.0011,0.0064,0.0149,0.0216,0.0356,0.053,0.0728,0.1111,0.165,0.2113,0.2534,0.2645,1.0,0.2152,0.1862,0.1542,0.1456,0.0986,0.0722,0.0977,0.0828,0.0666,0.0606,0.0477,0.0378,0.0374,0.0303,0.0238,0.0207,0.0164,0.0141,0.0109,0.0087,0.0071,0.006,0.0047,0.0043,0.0028,0.0022,0.002,0.0017,0.0011,9.0E-4,8.0E-4,7.0E-4,5.0E-4,5.0E-4,4.0E-4,2.0E-4,2.0E-4,1.0E-4},{0.0,1.0E-4,9.0E-4,0.0026,0.01,0.0158,0.028,0.0451,0.0643,0.0816,0.1311,0.1755,0.2225,0.2388,0.2764,1.0,0.2238,0.1932,0.1842,0.1398,0.1006,0.0742,0.1012,0.0851,0.0751,0.0624,0.0499,0.0395,0.04,0.0323,0.0275,0.0224,0.0184,0.015,0.0117,0.0104,0.0081,0.0074,0.0053,0.0046,0.004,0.003,0.0025,0.0019,0.0015,0.0011,8.0E-4,7.0E-4,6.0E-4,6.0E-4,5.0E-4,2.0E-4,2.0E-4},{0.0,0.0,7.0E-4,0.0029,0.0059,0.011,0.0225,0.0352,0.0528,0.0719,0.0968,0.1389,0.1868,0.2058,0.2511,0.2871,1.0,0.2339,0.2267,0.1855,0.1446,0.1058,0.0776,0.1039,0.0933,0.0803,0.0649,0.0529,0.0423,0.0421,0.0355,0.0303,0.0247,0.0203,0.017,0.0135,0.0116,0.0096,0.0077,0.0061,0.0056,0.0045,0.0036,0.003,0.002,0.0021,0.0014,0.0011,9.0E-4,8.0E-4,6.0E-4,5.0E-4,5.0E-4},{0.0,0.0,8.0E-4,0.0025,0.0055,0.0096,0.0182,0.0311,0.0454,0.0635,0.0862,0.1083,0.1536,0.1761,0.2234,0.2655,0.3023,0.3089,0.441,0.4007,0.3643,0.3234,0.2839,0.1303,0.1426,0.1267,0.1136,0.0991,0.0848,0.0537,0.0502,0.0437,0.0368,0.0312,0.0259,0.0191,0.0154,0.0127,0.0102,0.0089,0.0074,0.0066,0.0044,0.0044,0.0031,0.0026,0.0022,0.0016,0.0014,0.0011,8.0E-4,6.0E-4,5.0E-4},{0.0,0.0,8.0E-4,0.0021,0.0048,0.0082,0.0129,0.023,0.0366,0.0541,0.0707,0.0954,0.1174,0.14,0.182,0.2287,0.264,0.2872,1.0,0.2265,0.199,0.1691,0.1359,0.1284,0.0981,0.1223,0.1056,0.089,0.0736,0.0657,0.0552,0.0516,0.0437,0.035,0.0288,0.0248,0.0215,0.0179,0.0142,0.0121,0.0099,0.0081,0.0068,0.0055,0.0044,0.0037,0.003,0.0022,0.0019,0.0018,0.0014,9.0E-4,7.0E-4},{0.0,0.0,0.0,0.0011,0.0031,0.0053,0.0098,0.017,0.0278,0.0421,0.0609,0.082,0.106,0.1232,0.1655,0.2104,0.2549,0.2887,0.2983,1.0,0.2431,0.2116,0.1771,0.1692,0.1244,0.0968,0.1233,0.1075,0.0925,0.083,0.0662,0.0568,0.0543,0.0445,0.0373,0.0325,0.0256,0.0225,0.0193,0.0158,0.0127,0.0107,0.0089,0.0076,0.0069,0.0051,0.0046,0.0033,0.0028,0.0021,0.0019,0.0015,0.0013},{0.0,0.0,0.0,4.0E-4,0.002,0.0041,0.0076,0.013,0.0208,0.0332,0.0496,0.0692,0.0904,0.1096,0.1317,0.1733,0.2188,0.2584,0.2774,0.3127,1.0,0.2504,0.2172,0.2093,0.1636,0.125,0.0965,0.1253,0.1082,0.0984,0.0835,0.0688,0.0577,0.0555,0.0477,0.0405,0.0348,0.028,0.0247,0.0208,0.0176,0.0149,0.0124,0.0103,0.0087,0.0067,0.0059,0.0042,0.004,0.0031,0.0027,0.0021,0.0016},{0.0,0.0,0.0,3.0E-4,0.0011,0.0028,0.0056,0.0101,0.0166,0.0259,0.0402,0.0566,0.0756,0.0945,0.1167,0.1405,0.1819,0.2269,0.2465,0.2888,0.3216,1.0,0.2572,0.2505,0.2058,0.166,0.1259,0.0966,0.127,0.1166,0.1001,0.085,0.0714,0.0595,0.0581,0.0521,0.0426,0.0372,0.0306,0.027,0.0216,0.0188,0.0159,0.0134,0.0112,0.0088,0.008,0.0067,0.0053,0.0039,0.0034,0.0029,0.0024},{0.0,0.0,0.0,3.0E-4,9.0E-4,0.0021,0.0044,0.0084,0.0134,0.021,0.0304,0.0453,0.0639,0.0806,0.1013,0.1262,0.1479,0.1895,0.213,0.26,0.2986,0.3323,1.0,0.2924,0.2463,0.2104,0.1668,0.1279,0.0981,0.1348,0.1189,0.1026,0.0874,0.074,0.0629,0.061,0.0536,0.0453,0.0387,0.0322,0.0273,0.0238,0.0196,0.0174,0.0145,0.012,0.0101,0.0084,0.0068,0.0059,0.0046,0.0042,0.0031},{0.0,0.0,0.0,1.0E-4,7.0E-4,0.0015,0.003,0.0064,0.0109,0.017,0.0252,0.0355,0.0519,0.066,0.085,0.1091,0.1319,0.1551,0.181,0.2222,0.2621,0.2946,0.3189,1.0,0.2743,0.2381,0.2026,0.1635,0.1267,0.0964,0.1313,0.1176,0.1015,0.0879,0.0759,0.061,0.0613,0.054,0.0473,0.0403,0.0342,0.0298,0.0258,0.0217,0.0181,0.0159,0.0131,0.0108,0.0093,0.0078,0.0066,0.0059,0.0044},{0.0,0.0,0.0,1.0E-4,2.0E-4,0.0012,0.0023,0.0043,0.0078,0.013,0.0204,0.0292,0.0418,0.0538,0.0715,0.0916,0.114,0.139,0.1607,0.2036,0.2441,0.2848,0.32,0.3508,1.0,0.2802,0.2458,0.2058,0.1691,0.131,0.097,0.1352,0.119,0.1038,0.091,0.0759,0.0642,0.0642,0.0563,0.048,0.0418,0.0355,0.0312,0.0262,0.0233,0.0197,0.0164,0.014,0.012,0.011,0.0087,0.0071,0.0063},{0.0,0.0,0.0,0.0,2.0E-4,9.0E-4,0.0018,0.0028,0.0057,0.0103,0.0152,0.0234,0.0334,0.0457,0.0619,0.0806,0.1005,0.123,0.1433,0.1659,0.2097,0.2508,0.2928,0.3314,0.3588,0.3632,0.4638,0.4281,0.3893,0.3448,0.3121,0.1618,0.1659,0.1496,0.1363,0.1243,0.108,0.0767,0.0705,0.0635,0.0551,0.0477,0.0415,0.0323,0.0285,0.0248,0.0204,0.0178,0.0146,0.0127,0.0107,0.0088,0.0077},{0.0,0.0,0.0,0.0,2.0E-4,6.0E-4,0.0014,0.0026,0.0043,0.0073,0.0129,0.0189,0.0274,0.0381,0.0505,0.0662,0.0849,0.1079,0.1266,0.1492,0.1747,0.2163,0.2613,0.3042,0.3378,0.3663,1.0,0.2689,0.2353,0.2004,0.1651,0.1544,0.1215,0.1508,0.1341,0.1155,0.0986,0.0885,0.0758,0.0734,0.0631,0.0532,0.0464,0.0403,0.0362,0.0309,0.0259,0.0219,0.0189,0.0161,0.0136,0.0114,0.0103},{0.0,0.0,0.0,0.0,2.0E-4,5.0E-4,8.0E-4,0.0017,0.0033,0.0058,0.0096,0.0151,0.0223,0.0307,0.0414,0.0538,0.0713,0.0915,0.1106,0.1319,0.1563,0.1796,0.2219,0.2676,0.3091,0.3419,0.3477,1.0,0.2784,0.2439,0.2057,0.1925,0.1464,0.1189,0.1521,0.1343,0.1147,0.1054,0.0887,0.0771,0.0738,0.0645,0.0558,0.049,0.0427,0.0383,0.0317,0.0268,0.0234,0.0204,0.0172,0.0147,0.0129},{0.0,0.0,0.0,0.0,1.0E-4,4.0E-4,8.0E-4,0.0013,0.0028,0.0045,0.0072,0.0117,0.0183,0.0255,0.0337,0.0464,0.0589,0.0762,0.0951,0.1155,0.1378,0.1637,0.1885,0.2334,0.2784,0.314,0.3266,0.3569,1.0,0.2817,0.2495,0.2328,0.188,0.1476,0.1191,0.154,0.1333,0.1217,0.1065,0.0907,0.0789,0.0766,0.0665,0.0579,0.0509,0.0432,0.0385,0.0338,0.0293,0.0257,0.0208,0.0185,0.0156},{0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,4.0E-4,0.0011,0.002,0.0036,0.0057,0.0095,0.014,0.02,0.0284,0.0379,0.0496,0.0643,0.0803,0.1,0.1236,0.1459,0.1706,0.1972,0.2382,0.2815,0.298,0.3366,0.3656,1.0,0.2875,0.2766,0.2281,0.1872,0.1478,0.1189,0.1545,0.1404,0.1226,0.1091,0.0935,0.0817,0.0774,0.0692,0.0591,0.0527,0.0469,0.0413,0.0349,0.0298,0.0262,0.0233,0.0191},{0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,3.0E-4,8.0E-4,0.0015,0.0026,0.0047,0.0069,0.0111,0.0167,0.0236,0.0323,0.0428,0.0562,0.0697,0.0872,0.1093,0.1297,0.1516,0.1803,0.2033,0.2464,0.2639,0.3087,0.3455,0.3727,0.376,0.4941,0.4529,0.4113,0.3694,0.3275,0.1816,0.1907,0.1725,0.1569,0.1399,0.1257,0.0914,0.0848,0.0766,0.0666,0.0599,0.0539,0.0425,0.0377,0.0312,0.0278,0.0246},{0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,7.0E-4,0.0012,0.0019,0.0033,0.006,0.0087,0.013,0.0188,0.0262,0.0353,0.0463,0.059,0.072,0.0927,0.1117,0.1357,0.158,0.1843,0.2097,0.2318,0.2686,0.3096,0.3388,0.3598,1.0,0.2784,0.2439,0.2112,0.1798,0.1689,0.1367,0.1687,0.1496,0.1307,0.1142,0.1046,0.0914,0.0871,0.0769,0.0654,0.0588,0.0515,0.0449,0.0396,0.034,0.0292},{0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,4.0E-4,8.0E-4,0.0016,0.0029,0.0043,0.0066,0.011,0.0142,0.0213,0.0292,0.0385,0.048,0.063,0.0765,0.0972,0.1184,0.1396,0.1637,0.1929,0.2124,0.2486,0.2943,0.3287,0.3582,0.3651,1.0,0.2875,0.2564,0.2184,0.2078,0.1643,0.132,0.1682,0.1507,0.1339,0.1203,0.1048,0.0924,0.0883,0.0774,0.0671,0.0603,0.0526,0.0462,0.04,0.0353},{0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,4.0E-4,6.0E-4,0.0013,0.0021,0.0038,0.0052,0.0087,0.0123,0.017,0.0234,0.0317,0.0414,0.0539,0.0675,0.0823,0.1012,0.1242,0.1445,0.1713,0.1918,0.2156,0.2561,0.2995,0.3334,0.347,0.3722,1.0,0.2965,0.2646,0.2471,0.1991,0.1602,0.1324,0.1681,0.1501,0.1386,0.1215,0.1064,0.0922,0.0889,0.0778,0.0702,0.0626,0.0532,0.048,0.0413},{0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,5.0E-4,9.0E-4,0.0016,0.0028,0.0043,0.0069,0.0095,0.0142,0.02,0.0261,0.0345,0.0444,0.0577,0.0714,0.0846,0.1053,0.1288,0.1516,0.1737,0.1975,0.2242,0.2599,0.3031,0.3189,0.3545,0.3843,1.0,0.3037,0.291,0.2427,0.2014,0.1632,0.1351,0.1676,0.1559,0.1404,0.1227,0.1079,0.0944,0.0919,0.0809,0.0719,0.0625,0.0567,0.0503},{0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,4.0E-4,7.0E-4,0.0011,0.002,0.0035,0.0051,0.0077,0.0115,0.0159,0.0224,0.0291,0.0382,0.0489,0.0618,0.0759,0.092,0.1102,0.1332,0.1551,0.1769,0.2034,0.2252,0.2651,0.2882,0.3265,0.3631,0.3885,1.0,0.3258,0.2861,0.2447,0.2016,0.1613,0.132,0.1771,0.1565,0.1425,0.1221,0.1086,0.096,0.0929,0.0828,0.0744,0.065,0.0575},{0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,5.0E-4,0.001,0.0017,0.0027,0.0041,0.0066,0.0093,0.0131,0.0181,0.0223,0.0309,0.0408,0.053,0.0647,0.0786,0.0951,0.1151,0.1363,0.1589,0.185,0.208,0.2302,0.2523,0.2926,0.3273,0.355,0.3803,1.0,0.3103,0.2733,0.2397,0.1973,0.1605,0.1291,0.1691,0.1526,0.1355,0.1231,0.109,0.095,0.0938,0.0832,0.0747,0.0665},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,3.0E-4,6.0E-4,0.0014,0.002,0.003,0.005,0.0073,0.0109,0.0145,0.0198,0.0264,0.0338,0.043,0.0558,0.0682,0.0841,0.1015,0.1169,0.1402,0.1622,0.1885,0.2104,0.2322,0.2721,0.3117,0.343,0.3773,0.4002,1.0,0.3165,0.2786,0.2385,0.1977,0.1622,0.1295,0.1714,0.1554,0.1426,0.1245,0.1101,0.097,0.0951,0.0862,0.0747},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,4.0E-4,4.0E-4,0.001,0.0015,0.0025,0.0041,0.0058,0.009,0.012,0.0167,0.023,0.0294,0.0366,0.0466,0.0597,0.0716,0.0902,0.1031,0.1248,0.1453,0.1695,0.1925,0.2126,0.2352,0.2765,0.317,0.35,0.3852,0.4099,0.4086,0.4998,0.4627,0.421,0.3802,0.344,0.1981,0.2014,0.1847,0.1726,0.1544,0.142,0.1101,0.1011,0.0933},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,4.0E-4,7.0E-4,0.0013,0.0019,0.0032,0.0051,0.0073,0.01,0.0132,0.0183,0.0241,0.0319,0.0404,0.0508,0.0636,0.0753,0.0922,0.1088,0.1269,0.1519,0.175,0.1949,0.2197,0.244,0.2817,0.3214,0.3614,0.3888,0.4116,1.0,0.3036,0.2694,0.2318,0.1948,0.1837,0.1527,0.1875,0.1665,0.1496,0.1329,0.1186,0.1069,0.1031},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,2.0E-4,4.0E-4,6.0E-4,0.0011,0.0015,0.0026,0.0039,0.0059,0.0078,0.0114,0.0152,0.0195,0.025,0.0336,0.0419,0.053,0.0678,0.0798,0.0956,0.1133,0.1332,0.1546,0.1737,0.2007,0.2256,0.2486,0.2854,0.3267,0.365,0.3986,0.3956,1.0,0.3078,0.2721,0.2382,0.2221,0.179,0.1503,0.1863,0.1672,0.1485,0.1378,0.1224,0.1083},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,3.0E-4,3.0E-4,7.0E-4,0.0013,0.002,0.0031,0.0047,0.0065,0.0095,0.0132,0.0165,0.0222,0.0283,0.0351,0.0458,0.0565,0.0682,0.0838,0.1005,0.114,0.1352,0.1565,0.1812,0.2044,0.2299,0.2488,0.2955,0.3318,0.3698,0.3772,0.4024,1.0,0.3166,0.277,0.2634,0.215,0.1739,0.1455,0.1859,0.1669,0.154,0.1365,0.1193},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,3.0E-4,6.0E-4,8.0E-4,0.0017,0.0022,0.0037,0.0051,0.0076,0.0104,0.0137,0.018,0.0243,0.0307,0.0384,0.0485,0.0606,0.0737,0.0869,0.1023,0.1222,0.1371,0.1611,0.1829,0.205,0.2339,0.2571,0.2963,0.3365,0.3494,0.3829,0.4093,1.0,0.3202,0.3046,0.2586,0.2143,0.1757,0.1459,0.1854,0.1716,0.1539,0.1379},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,5.0E-4,7.0E-4,0.0013,0.002,0.0029,0.0042,0.0064,0.0085,0.0112,0.0148,0.0206,0.0257,0.0332,0.0408,0.0516,0.0644,0.0759,0.0887,0.1063,0.1247,0.1447,0.1664,0.1911,0.2159,0.2365,0.2636,0.3006,0.3215,0.3622,0.3927,0.4131,0.4136,0.5237,0.4774,0.4364,0.3948,0.3562,0.2149,0.2188,0.2028,0.1857},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,6.0E-4,8.0E-4,0.0016,0.0023,0.0036,0.0053,0.007,0.0096,0.0122,0.017,0.0218,0.0283,0.0359,0.0448,0.0538,0.0649,0.0796,0.0928,0.1099,0.1288,0.1479,0.17,0.1925,0.2174,0.2456,0.2686,0.2863,0.3223,0.3569,0.3823,0.3984,1.0,0.3055,0.2721,0.2372,0.2055,0.1919,0.1656,0.2007,0.1811},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,5.0E-4,7.0E-4,0.0013,0.0018,0.0027,0.004,0.0058,0.0074,0.0106,0.0143,0.0188,0.0241,0.0299,0.0374,0.0453,0.0559,0.0699,0.0822,0.096,0.1139,0.1317,0.1515,0.176,0.199,0.2223,0.2473,0.2659,0.3056,0.3398,0.3739,0.4015,0.4035,1.0,0.3184,0.2817,0.2459,0.2348,0.1881,0.1601,0.1967},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,1.0E-4,3.0E-4,6.0E-4,0.001,0.0014,0.0023,0.0034,0.0046,0.0068,0.009,0.0123,0.0149,0.0192,0.0254,0.0321,0.0402,0.0499,0.059,0.0726,0.0854,0.0999,0.1166,0.1338,0.1556,0.1793,0.202,0.2272,0.2476,0.2701,0.3102,0.3482,0.3787,0.3879,0.414,1.0,0.3206,0.287,0.2716,0.2262,0.1873,0.158},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,5.0E-4,7.0E-4,0.0012,0.0016,0.0027,0.0033,0.0051,0.007,0.0091,0.0129,0.0162,0.0216,0.0275,0.0332,0.0429,0.0504,0.0627,0.0754,0.0873,0.104,0.1199,0.1391,0.1609,0.1818,0.2074,0.2292,0.2514,0.273,0.3116,0.3486,0.3651,0.3953,0.4211,1.0,0.3266,0.3145,0.2681,0.2261,0.1849},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,3.0E-4,4.0E-4,6.0E-4,9.0E-4,0.0015,0.0022,0.0033,0.0041,0.006,0.0084,0.0109,0.0139,0.0176,0.0238,0.0288,0.0365,0.0446,0.0538,0.0657,0.0785,0.0919,0.1077,0.1241,0.1423,0.1645,0.1875,0.208,0.2313,0.2554,0.2778,0.3181,0.3333,0.3718,0.3977,0.4243,1.0,0.3515,0.3095,0.2648,0.222},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,4.0E-4,7.0E-4,0.001,0.0017,0.0025,0.0036,0.0046,0.0065,0.0092,0.0121,0.0155,0.0202,0.0258,0.0312,0.038,0.0474,0.0568,0.0684,0.0809,0.0959,0.1077,0.1243,0.1475,0.1667,0.1895,0.2098,0.236,0.2578,0.281,0.3007,0.3364,0.3661,0.3935,0.4113,1.0,0.3329,0.2982,0.2575},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,1.0E-4,3.0E-4,6.0E-4,8.0E-4,0.0011,0.0019,0.0029,0.0036,0.0056,0.0074,0.01,0.0138,0.0172,0.0211,0.0262,0.034,0.0416,0.0498,0.0595,0.072,0.0837,0.0975,0.1143,0.1311,0.1504,0.1694,0.1925,0.2146,0.2401,0.2606,0.2843,0.3187,0.3558,0.3844,0.4135,0.435,1.0,0.3381,0.2977},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,5.0E-4,6.0E-4,0.001,0.0017,0.0023,0.0031,0.0046,0.0061,0.0083,0.0112,0.0144,0.0185,0.0223,0.0286,0.0352,0.0419,0.0519,0.0634,0.0728,0.0867,0.1008,0.1164,0.1325,0.1523,0.1747,0.1931,0.2182,0.2432,0.2635,0.2852,0.323,0.3576,0.3895,0.4208,0.4388,0.4356,0.5213},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,4.0E-4,5.0E-4,8.0E-4,0.0012,0.0019,0.0026,0.0041,0.0054,0.007,0.0093,0.0119,0.0152,0.0194,0.0245,0.0297,0.038,0.0453,0.0538,0.0661,0.0771,0.0896,0.1039,0.1201,0.1366,0.1575,0.1769,0.1991,0.2233,0.2446,0.267,0.2876,0.3267,0.3583,0.3955,0.4251,0.4432,1.0} };
	private final double oppCapture[][] = { {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.1996,0.2,0.2007,0.2002,0.1999,0.1631,0.0338,0.0339,0.0325,0.0328,0.0324,0.0227,0.0052,0.0051,0.0049,0.005,0.005,0.0,0.0,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.1986,0.205,0.211,0.2147,0.2225,0.1897,0.0553,0.0513,0.0483,0.0445,0.041,0.0333,0.0116,0.0105,0.0094,0.0075,0.0067,0.0023,0.0016,0.0011,0.001,5.0E-4,3.0E-4,2.0E-4,3.0E-4,2.0E-4,1.0E-4,0.0,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0,2.0E-4,0.0,0.2056,0.2158,0.2289,0.2398,0.246,0.2126,0.0769,0.0697,0.061,0.0559,0.0517,0.0425,0.0198,0.0166,0.0136,0.0113,0.0092,0.0048,0.0035,0.0028,0.0017,0.0012,7.0E-4,6.0E-4,5.0E-4,4.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0,1.0E-4,0.0058,0.0162,0.017,0.0342,0.0531,0.0641,0.0682,0.0763,0.0651,0.053,0.0414,0.0357,0.0293,0.0289,0.0232,0.0182,0.0142,0.0107,0.0086,0.0067,0.0061,0.0041,0.0024,0.002,0.0013,0.0012,7.0E-4,6.0E-4,4.0E-4,3.0E-4,2.0E-4,2.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0,2.0E-4,0.0061,0.0172,0.0,0.2341,0.2509,0.2681,0.2722,0.2659,0.232,0.095,0.0861,0.0775,0.0709,0.0655,0.0523,0.0291,0.024,0.02,0.0177,0.0153,0.0089,0.0067,0.005,0.0039,0.0026,0.002,0.0017,0.0012,9.0E-4,6.0E-4,5.0E-4,3.0E-4,2.0E-4,2.0E-4,2.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0,2.0E-4,0.0063,0.0176,0.0339,0.0,0.2509,0.274,0.2831,0.2836,0.2834,0.2576,0.1183,0.1061,0.0954,0.0849,0.0763,0.0642,0.0385,0.0319,0.0274,0.0224,0.0176,0.0117,0.0089,0.0068,0.0054,0.0036,0.0031,0.0021,0.0017,0.0013,0.001,7.0E-4,5.0E-4,5.0E-4,3.0E-4,2.0E-4,2.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,2.0E-4,0.0067,0.019,0.034,0.0,0.2508,0.2742,0.2881,0.2992,0.2997,0.2717,0.1299,0.1197,0.1074,0.0937,0.086,0.07,0.0465,0.0384,0.0323,0.0266,0.022,0.0148,0.0112,0.0093,0.0069,0.0053,0.0039,0.0033,0.0025,0.0022,0.0012,0.0011,7.0E-4,6.0E-4,4.0E-4,3.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,1.0E-4,0.0017,0.0104,0.0192,0.0355,0.0,0.2547,0.2797,0.2993,0.3111,0.3122,0.2859,0.1455,0.1323,0.118,0.1064,0.0939,0.0806,0.0539,0.0446,0.0382,0.0299,0.026,0.0183,0.015,0.0113,0.0094,0.0069,0.0051,0.0039,0.003,0.0022,0.002,0.0016,9.0E-4,0.001,7.0E-4,5.0E-4,3.0E-4,3.0E-4,2.0E-4,2.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,0.0,0.0,0.0,0.0},{0.0,0.0,0.0,9.0E-4,0.0015,0.0048,0.0095,0.0209,0.0371,0.0,0.256,0.2858,0.31,0.3225,0.327,0.2963,0.1592,0.1432,0.1271,0.1154,0.1019,0.0902,0.0609,0.0514,0.0426,0.0365,0.0301,0.0223,0.0181,0.0139,0.0111,0.0085,0.0068,0.0055,0.0039,0.0032,0.0023,0.0021,0.0015,0.001,9.0E-4,7.0E-4,4.0E-4,3.0E-4,3.0E-4,3.0E-4,2.0E-4,2.0E-4,1.0E-4,0.0,1.0E-4,0.0,0.0},{0.0,0.0,0.0,0.001,0.0032,0.0074,0.0126,0.0235,0.0408,0.0639,0.0,0.2832,0.3144,0.3349,0.3456,0.3439,0.3171,0.1765,0.1604,0.1425,0.1298,0.1153,0.0997,0.0692,0.0597,0.0496,0.0416,0.0349,0.0259,0.0212,0.0169,0.0126,0.0103,0.0086,0.0066,0.0052,0.0043,0.0032,0.0028,0.0018,0.0017,0.001,8.0E-4,7.0E-4,5.0E-4,4.0E-4,4.0E-4,2.0E-4,2.0E-4,1.0E-4,1.0E-4,1.0E-4,1.0E-4},{0.0,0.0,0.0,9.0E-4,0.0032,0.0067,0.0094,0.0145,0.0252,0.0413,0.062,0.0,0.2914,0.3196,0.3442,0.3534,0.3524,0.3284,0.1871,0.1707,0.1534,0.1401,0.1238,0.1098,0.076,0.066,0.0554,0.0469,0.0398,0.0299,0.0238,0.0196,0.0162,0.0126,0.0102,0.0083,0.0062,0.0053,0.0041,0.003,0.0025,0.0017,0.0014,0.0012,9.0E-4,7.0E-4,5.0E-4,4.0E-4,4.0E-4,3.0E-4,2.0E-4,2.0E-4,1.0E-4},{0.0,0.0,0.0,0.001,0.0028,0.0071,0.0103,0.0139,0.0182,0.0297,0.0471,0.0703,0.0991,0.0951,0.1284,0.151,0.1608,0.1644,0.1952,0.1648,0.1474,0.1318,0.1164,0.1007,0.094,0.0807,0.0676,0.0574,0.0479,0.04,0.0338,0.0275,0.0235,0.0183,0.0151,0.0117,0.0091,0.0072,0.0061,0.005,0.004,0.0031,0.0024,0.0017,0.0015,0.0012,9.0E-4,8.0E-4,5.0E-4,4.0E-4,3.0E-4,3.0E-4,2.0E-4},{0.0,0.0,0.0,1.0E-4,0.0011,0.0039,0.0067,0.0118,0.0151,0.0208,0.0344,0.0512,0.0749,0.0,0.3052,0.3348,0.351,0.3568,0.3498,0.3234,0.1877,0.1759,0.1582,0.1461,0.1342,0.1136,0.0845,0.0735,0.0643,0.0551,0.0464,0.0362,0.0296,0.0241,0.0209,0.016,0.014,0.0116,0.0087,0.0067,0.0056,0.0047,0.0036,0.0028,0.0022,0.0016,0.0014,0.0012,8.0E-4,7.0E-4,5.0E-4,4.0E-4,3.0E-4},{0.0,0.0,0.0,0.0,4.0E-4,0.0021,0.0043,0.0089,0.0146,0.018,0.0259,0.0378,0.057,0.0863,0.0,0.3058,0.3345,0.3549,0.3539,0.3587,0.3362,0.2012,0.1837,0.1706,0.1553,0.1406,0.1224,0.0924,0.0793,0.0698,0.0598,0.0505,0.0393,0.0331,0.0274,0.0234,0.0188,0.0149,0.0131,0.0101,0.0081,0.0066,0.005,0.0043,0.0036,0.0028,0.0024,0.0016,0.0012,0.0011,9.0E-4,6.0E-4,7.0E-4},{0.0,0.0,0.0,2.0E-4,5.0E-4,0.001,0.0028,0.0055,0.0098,0.0178,0.0223,0.0299,0.0452,0.0678,0.0883,0.0,0.3089,0.3356,0.3474,0.3627,0.3732,0.3465,0.2109,0.1953,0.1776,0.1619,0.1438,0.1296,0.0987,0.088,0.0748,0.0637,0.0545,0.0439,0.0371,0.0304,0.0253,0.0212,0.0169,0.014,0.0113,0.009,0.0074,0.0063,0.0047,0.0039,0.0031,0.0026,0.0022,0.0018,0.0012,0.0013,8.0E-4},{0.0,0.0,0.0,2.0E-4,6.0E-4,0.0014,0.0031,0.0058,0.0107,0.0157,0.0236,0.0279,0.0376,0.0546,0.069,0.0908,0.0,0.3038,0.3316,0.3544,0.3714,0.3754,0.3556,0.2171,0.2037,0.1826,0.1711,0.1534,0.1344,0.106,0.0916,0.0784,0.0669,0.0597,0.0471,0.0406,0.0333,0.0274,0.0232,0.0197,0.0162,0.0132,0.01,0.0092,0.0074,0.0052,0.0051,0.0035,0.0033,0.0027,0.0019,0.0015,0.0012},{0.0,0.0,0.0,2.0E-4,6.0E-4,0.0012,0.002,0.004,0.007,0.0117,0.0181,0.0269,0.0319,0.0443,0.0529,0.0689,0.0877,0.1166,0.1038,0.139,0.1625,0.1806,0.1851,0.2209,0.1949,0.1755,0.1601,0.1418,0.1283,0.1222,0.1066,0.0914,0.0777,0.0678,0.0596,0.0516,0.0431,0.0367,0.0305,0.0248,0.0214,0.0178,0.0142,0.0122,0.0103,0.0076,0.0063,0.0052,0.0043,0.0038,0.003,0.0023,0.0019},{0.0,0.0,0.0,2.0E-4,6.0E-4,0.0011,0.0022,0.0028,0.0053,0.0085,0.0131,0.0203,0.0297,0.0424,0.0529,0.0681,0.0877,0.1122,0.0,0.3295,0.3581,0.3765,0.3836,0.3731,0.3486,0.222,0.2051,0.1902,0.1748,0.1601,0.1407,0.1105,0.098,0.0859,0.0746,0.066,0.054,0.0452,0.0386,0.0335,0.0274,0.0229,0.0194,0.0161,0.0142,0.011,0.0095,0.0074,0.006,0.0052,0.0042,0.0034,0.0026},{0.0,0.0,0.0,0.0,2.0E-4,6.0E-4,0.0015,0.0028,0.0041,0.0063,0.0101,0.0155,0.0242,0.0346,0.0406,0.0501,0.0636,0.0839,0.1097,0.0,0.3266,0.3526,0.375,0.3763,0.3809,0.3583,0.2289,0.2125,0.1976,0.1835,0.1681,0.1462,0.1166,0.1043,0.0907,0.0786,0.0684,0.0583,0.0484,0.0407,0.0359,0.0304,0.0253,0.0207,0.018,0.0144,0.0125,0.0103,0.0084,0.0067,0.0059,0.0048,0.004},{0.0,0.0,0.0,0.0,1.0E-4,3.0E-4,0.0011,0.002,0.0034,0.0051,0.0081,0.0129,0.0192,0.0269,0.0379,0.0443,0.0533,0.0686,0.0912,0.1078,0.0,0.3241,0.3544,0.3689,0.3839,0.389,0.3677,0.2371,0.2208,0.2048,0.1889,0.1724,0.1555,0.1241,0.1078,0.0956,0.0847,0.0721,0.0609,0.0519,0.0443,0.039,0.0332,0.0271,0.0234,0.0183,0.0158,0.0136,0.0116,0.0095,0.0076,0.0066,0.0052},{0.0,0.0,0.0,0.0,0.0,2.0E-4,5.0E-4,0.0011,0.0024,0.0046,0.0064,0.0108,0.0154,0.0223,0.0308,0.0404,0.0477,0.0568,0.0733,0.0873,0.1089,0.0,0.3235,0.3472,0.3723,0.3895,0.3977,0.377,0.2451,0.2282,0.2105,0.1939,0.1801,0.161,0.129,0.1139,0.1003,0.0884,0.0767,0.0632,0.0552,0.0478,0.0412,0.0347,0.0292,0.0244,0.0217,0.0171,0.0144,0.0124,0.0108,0.0088,0.0073},{0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,3.0E-4,0.001,0.0023,0.0038,0.0057,0.0078,0.0115,0.0172,0.0255,0.0337,0.0441,0.0506,0.0641,0.0725,0.0891,0.1048,0.0,0.3142,0.3533,0.3784,0.4005,0.3999,0.3844,0.2504,0.2343,0.2176,0.1998,0.1844,0.1652,0.1339,0.1195,0.1062,0.093,0.0827,0.0697,0.0577,0.051,0.0432,0.0364,0.0315,0.0272,0.0228,0.0187,0.0161,0.013,0.0116,0.01},{0.0,0.0,0.0,0.0,0.0,1.0E-4,3.0E-4,7.0E-4,0.0014,0.0026,0.0044,0.0071,0.0096,0.0147,0.0198,0.0266,0.0362,0.0466,0.0583,0.0723,0.0857,0.104,0.1294,0.0,0.3362,0.3737,0.397,0.4134,0.4158,0.3964,0.2653,0.2446,0.2273,0.2096,0.1947,0.1754,0.1406,0.1241,0.1113,0.098,0.0868,0.0722,0.0627,0.0538,0.0462,0.0392,0.0341,0.0291,0.0242,0.0207,0.0184,0.0154,0.0124},{0.0,0.0,0.0,0.0,0.0,0.0,2.0E-4,5.0E-4,9.0E-4,0.0018,0.0035,0.0055,0.0083,0.0127,0.0177,0.0246,0.0315,0.0406,0.0524,0.0599,0.0679,0.0827,0.1015,0.1232,0.0,0.3376,0.3746,0.4035,0.4157,0.4198,0.4015,0.2714,0.253,0.2331,0.2168,0.2011,0.1768,0.147,0.1296,0.1159,0.1018,0.0896,0.0758,0.0662,0.0566,0.0484,0.0407,0.0349,0.0306,0.0268,0.0215,0.0191,0.016},{0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,4.0E-4,7.0E-4,0.001,0.0022,0.004,0.0063,0.0099,0.0138,0.0181,0.0258,0.033,0.0441,0.056,0.0611,0.0705,0.0833,0.1012,0.1261,0.1598,0.1435,0.1792,0.2076,0.2249,0.2284,0.2718,0.2435,0.2268,0.2069,0.1853,0.1741,0.1641,0.145,0.1305,0.1139,0.1003,0.0896,0.0796,0.0696,0.0594,0.0531,0.0441,0.0383,0.0327,0.028,0.0243,0.0194},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,2.0E-4,5.0E-4,0.0011,0.0016,0.0031,0.0048,0.0081,0.0118,0.0147,0.0203,0.0266,0.0362,0.045,0.0575,0.0633,0.072,0.0857,0.1064,0.1293,0.0,0.3515,0.3856,0.4053,0.4181,0.406,0.3911,0.2645,0.2487,0.2332,0.2166,0.2017,0.1807,0.151,0.1359,0.1189,0.1095,0.0975,0.0814,0.071,0.0625,0.0544,0.0469,0.0406,0.0354,0.0301,0.0262},{0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,3.0E-4,9.0E-4,0.0014,0.0024,0.0039,0.0065,0.0087,0.0132,0.0173,0.0229,0.0303,0.0382,0.0476,0.0599,0.0659,0.0729,0.0886,0.1096,0.1411,0.0,0.3499,0.3836,0.404,0.4087,0.4164,0.3962,0.2718,0.2589,0.242,0.2239,0.2061,0.1866,0.157,0.142,0.1279,0.1113,0.1,0.0858,0.0752,0.0649,0.0568,0.0501,0.0425,0.0356,0.0322},{0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,4.0E-4,6.0E-4,0.0011,0.002,0.0029,0.0048,0.0067,0.0104,0.0143,0.0189,0.0255,0.0312,0.0407,0.0488,0.0618,0.067,0.0779,0.0923,0.1175,0.1375,0.0,0.3523,0.3785,0.3963,0.415,0.4233,0.4004,0.2772,0.2617,0.2452,0.2288,0.2124,0.194,0.1581,0.1435,0.1306,0.1154,0.1028,0.0886,0.0789,0.0682,0.0595,0.0529,0.0446,0.0385},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,2.0E-4,5.0E-4,7.0E-4,0.0014,0.0021,0.0038,0.0053,0.0079,0.0114,0.0163,0.022,0.0273,0.0354,0.0433,0.0532,0.0669,0.0721,0.0813,0.1025,0.1159,0.1355,0.0,0.3462,0.3731,0.4016,0.4187,0.4293,0.4131,0.2849,0.268,0.25,0.2352,0.2158,0.1971,0.165,0.1478,0.1329,0.1187,0.1069,0.0917,0.0817,0.0724,0.0618,0.0556,0.0466},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,3.0E-4,6.0E-4,0.001,0.0019,0.0028,0.0042,0.0064,0.0095,0.013,0.0182,0.0229,0.0279,0.0367,0.046,0.0566,0.0671,0.0737,0.0901,0.0998,0.1143,0.1318,0.1624,0.1395,0.1777,0.2084,0.2268,0.2359,0.2801,0.256,0.238,0.2209,0.2038,0.1898,0.1812,0.1644,0.145,0.1311,0.119,0.1059,0.0954,0.0848,0.073,0.0637,0.0559},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,5.0E-4,6.0E-4,0.0013,0.0023,0.0032,0.0052,0.0076,0.0107,0.015,0.0205,0.0246,0.0309,0.0381,0.0474,0.0566,0.0716,0.086,0.0975,0.1117,0.1316,0.1552,0.0,0.3637,0.3996,0.4168,0.4283,0.421,0.4007,0.2772,0.2662,0.2519,0.2362,0.2184,0.2006,0.1678,0.1529,0.1373,0.1245,0.1118,0.0963,0.0854,0.0766,0.0657},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,3.0E-4,7.0E-4,0.0012,0.0017,0.0026,0.0041,0.0057,0.0087,0.0123,0.0166,0.0215,0.026,0.0326,0.0415,0.0487,0.059,0.0738,0.0813,0.0911,0.1043,0.1229,0.153,0.0,0.3576,0.3878,0.4149,0.4191,0.4233,0.4099,0.2834,0.2705,0.2563,0.2429,0.2238,0.2028,0.1719,0.1553,0.1416,0.1303,0.1138,0.1006,0.0883,0.0789},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,5.0E-4,0.001,0.0013,0.0022,0.0034,0.0048,0.0069,0.0096,0.0137,0.0178,0.0226,0.0283,0.0343,0.0427,0.0517,0.0626,0.0771,0.0852,0.0933,0.107,0.1298,0.1492,0.0,0.3559,0.3881,0.4053,0.423,0.4324,0.4163,0.2909,0.2773,0.2606,0.2435,0.2278,0.2061,0.1781,0.1597,0.144,0.1296,0.1169,0.1022,0.0909},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,3.0E-4,3.0E-4,7.0E-4,0.001,0.0018,0.0025,0.004,0.0058,0.008,0.0105,0.0141,0.0189,0.0244,0.03,0.0368,0.0454,0.0543,0.0674,0.0799,0.0859,0.0943,0.1142,0.1256,0.145,0.0,0.3529,0.3799,0.4093,0.4273,0.4363,0.4191,0.2994,0.279,0.2633,0.2491,0.2322,0.2123,0.1793,0.163,0.1489,0.1367,0.1221,0.1055},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,4.0E-4,5.0E-4,9.0E-4,0.0012,0.0021,0.0031,0.0049,0.0067,0.0086,0.0123,0.0164,0.0208,0.0264,0.0311,0.0382,0.0485,0.0564,0.0686,0.0812,0.0859,0.102,0.1121,0.1257,0.1442,0.0,0.3427,0.3831,0.4126,0.4326,0.4448,0.4269,0.2985,0.2856,0.2674,0.2526,0.2382,0.2184,0.1869,0.167,0.155,0.1363,0.1243},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,3.0E-4,5.0E-4,8.0E-4,0.0011,0.0018,0.0026,0.0037,0.0053,0.0073,0.01,0.0141,0.0178,0.0222,0.0278,0.0331,0.0415,0.0483,0.0585,0.0693,0.0831,0.0978,0.1091,0.1229,0.1417,0.1643,0.0,0.3676,0.4027,0.4272,0.444,0.4506,0.4378,0.31,0.2978,0.2793,0.2612,0.2431,0.2229,0.1892,0.1729,0.1554,0.142},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,5.0E-4,8.0E-4,0.0016,0.0021,0.0032,0.0043,0.0057,0.0081,0.0109,0.0142,0.0185,0.0236,0.0293,0.0362,0.0437,0.0527,0.063,0.0746,0.0888,0.0943,0.1019,0.1172,0.1336,0.1577,0.0,0.3668,0.4045,0.4307,0.4506,0.4551,0.4424,0.3165,0.3042,0.2805,0.264,0.2479,0.2287,0.1938,0.1759,0.1604},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,2.0E-4,3.0E-4,6.0E-4,0.0011,0.0016,0.0024,0.0034,0.005,0.0074,0.009,0.0124,0.0156,0.0205,0.0252,0.0311,0.0364,0.0444,0.0533,0.062,0.0766,0.0893,0.0952,0.1041,0.1183,0.1355,0.1557,0.1891,0.1712,0.2085,0.2387,0.2565,0.2617,0.3167,0.2888,0.2694,0.2554,0.2385,0.2181,0.2134,0.1946,0.1754},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,1.0E-4,2.0E-4,6.0E-4,9.0E-4,0.0011,0.002,0.0027,0.0044,0.0057,0.0079,0.0101,0.0126,0.0172,0.021,0.0265,0.0331,0.0389,0.0471,0.0552,0.0662,0.0769,0.0889,0.0951,0.1051,0.1177,0.1352,0.1593,0.0,0.3755,0.4082,0.4336,0.4466,0.4421,0.4278,0.3035,0.2962,0.2831,0.2653,0.2482,0.2259,0.1972},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,4.0E-4,5.0E-4,9.0E-4,0.0017,0.0023,0.0034,0.0045,0.0065,0.0082,0.0111,0.0134,0.0173,0.0229,0.0282,0.0343,0.0411,0.0487,0.0572,0.0687,0.0781,0.0919,0.0973,0.1054,0.1193,0.1371,0.169,0.0,0.374,0.4107,0.4301,0.4381,0.4442,0.4297,0.3151,0.2989,0.2839,0.2668,0.2497,0.2314},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,3.0E-4,4.0E-4,9.0E-4,0.0012,0.0019,0.0028,0.0039,0.0052,0.0069,0.0096,0.0121,0.0151,0.0194,0.0249,0.0292,0.0364,0.043,0.0514,0.0595,0.0689,0.0806,0.0937,0.0987,0.1095,0.1211,0.1493,0.1647,0.0,0.3711,0.4058,0.4212,0.4426,0.4534,0.4398,0.3164,0.3026,0.2874,0.2722,0.2534},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,3.0E-4,4.0E-4,7.0E-4,0.0011,0.0016,0.0023,0.0029,0.0041,0.0056,0.0073,0.0099,0.0129,0.0166,0.0211,0.0251,0.0309,0.0374,0.0447,0.0537,0.0624,0.0746,0.0845,0.0975,0.1024,0.1122,0.1346,0.1476,0.1624,0.0,0.3674,0.3945,0.4273,0.4467,0.4579,0.4433,0.3252,0.3068,0.2902,0.2781},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,6.0E-4,7.0E-4,0.0012,0.0017,0.0024,0.0033,0.0046,0.0065,0.0082,0.0106,0.014,0.0182,0.0214,0.0276,0.0309,0.04,0.0474,0.0531,0.0635,0.0731,0.0857,0.0997,0.1059,0.1189,0.1289,0.141,0.1631,0.1881,0.1626,0.2046,0.2332,0.2575,0.2636,0.3171,0.2943,0.2777,0.2608},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,4.0E-4,6.0E-4,0.001,0.0013,0.002,0.0029,0.0041,0.0048,0.0066,0.0087,0.0114,0.0149,0.0186,0.0229,0.028,0.0349,0.0415,0.0478,0.0563,0.0626,0.0751,0.0856,0.099,0.1153,0.1258,0.1391,0.1592,0.1829,0.0,0.387,0.4216,0.4427,0.4538,0.4501,0.4316,0.3131,0.305},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,4.0E-4,5.0E-4,7.0E-4,0.001,0.0016,0.0025,0.0035,0.0043,0.0059,0.0082,0.0099,0.0127,0.0158,0.019,0.0235,0.0304,0.0354,0.0421,0.0492,0.0569,0.0662,0.0762,0.0889,0.1036,0.1074,0.1188,0.1323,0.1487,0.1777,0.0,0.3782,0.4122,0.4368,0.4394,0.4524,0.4378,0.3226},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,3.0E-4,4.0E-4,6.0E-4,0.0011,0.0014,0.002,0.0027,0.0035,0.0054,0.0065,0.009,0.0108,0.0137,0.017,0.0211,0.0259,0.0309,0.0374,0.0448,0.0509,0.0577,0.0698,0.0776,0.0908,0.1044,0.1101,0.1194,0.1335,0.1564,0.1719,0.0,0.3764,0.4105,0.4263,0.4446,0.457,0.4454},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,1.0E-4,4.0E-4,6.0E-4,9.0E-4,0.0012,0.0017,0.0022,0.0031,0.004,0.0056,0.007,0.0088,0.0116,0.0154,0.0177,0.0218,0.0259,0.0318,0.0387,0.0452,0.0523,0.0609,0.07,0.0822,0.0933,0.1068,0.1119,0.1217,0.1388,0.1528,0.1697,0.0,0.3727,0.3985,0.4339,0.4504,0.4656},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,2.0E-4,4.0E-4,6.0E-4,9.0E-4,0.0013,0.0018,0.0025,0.0035,0.0044,0.0059,0.008,0.01,0.0131,0.0151,0.0185,0.0236,0.028,0.0338,0.0417,0.0476,0.0545,0.0622,0.0718,0.0834,0.0937,0.1082,0.1123,0.1275,0.1365,0.1519,0.1684,0.0,0.363,0.4025,0.4347,0.457},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,1.0E-4,2.0E-4,3.0E-4,4.0E-4,8.0E-4,0.0011,0.0015,0.0019,0.0029,0.0037,0.0049,0.0067,0.0084,0.0106,0.013,0.016,0.0203,0.0244,0.0302,0.0352,0.0416,0.0491,0.0551,0.0632,0.0729,0.0832,0.0967,0.1102,0.1234,0.1353,0.1469,0.1641,0.1895,0.0,0.3866,0.4196,0.4505},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,0.0,1.0E-4,2.0E-4,3.0E-4,3.0E-4,6.0E-4,8.0E-4,0.0013,0.0017,0.0021,0.0029,0.0041,0.0056,0.0069,0.0089,0.0117,0.0139,0.0175,0.0212,0.0262,0.0295,0.037,0.0428,0.0511,0.0593,0.0665,0.0771,0.0884,0.0989,0.1122,0.1189,0.1286,0.1402,0.1566,0.1776,0.0,0.3831,0.4222},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,2.0E-4,3.0E-4,4.0E-4,6.0E-4,0.001,0.0013,0.0019,0.0029,0.0036,0.0043,0.0058,0.0078,0.0099,0.0118,0.0152,0.0184,0.0218,0.0264,0.0324,0.0377,0.0442,0.0525,0.0579,0.068,0.0768,0.0867,0.1006,0.1127,0.1198,0.1295,0.1416,0.1563,0.1769,0.209,0.1883},{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0E-4,1.0E-4,2.0E-4,3.0E-4,6.0E-4,8.0E-4,0.0011,0.0016,0.0024,0.0031,0.0038,0.0051,0.0068,0.0079,0.0108,0.0128,0.0159,0.0186,0.0239,0.0286,0.0325,0.0395,0.0458,0.0536,0.0591,0.0696,0.0786,0.0892,0.1022,0.1151,0.12,0.1282,0.14,0.1569,0.177,0.0} };


	@Override
	public List<Move> getMoveList(GameState gameState, List<Integer> diceThrow) {
		List<Move> moves = new ArrayList<Move>();
		Wrapper max = new Wrapper();
		
		int n = diceThrow.size();
		for(int i=0;i<n;++i) {
			findOptimalMoves(0,diceThrow,gameState,moves,max,0);
			Collections.swap(diceThrow,0,n-i-1);
		}
		
		
		if(max.moves.size() == 0)
			max.moves.add(new Move(0,0));
		System.err.println("Score: " + max.val);
		
		return max.moves;
	}

	private void findOptimalMoves(int i, List<Integer> diceThrow, GameState gameState, List<Move> moves, Wrapper max, int rolls) {
		
		double val = calculateScore(gameState,rolls);
		if(moves.size() > max.moves.size() || (val > max.val && moves.size() == max.moves.size())) {
			max.val = val;
			max.moves.clear();
			for(int j=0; j<moves.size(); ++j)
				max.moves.add(moves.get(j).copy()); 
		}
		
		if(i == diceThrow.size()) {
			//System.err.println(moves);
			return ;
		}
		for(int j=0; j<4; ++j) {
			Move move = new Move(j,diceThrow.get(i));
			boolean isValid = gameState.validateMove(0,move);
			if(isValid) {
				GameState dummyGameState = new GameState(gameState);
				int extraRolls = dummyGameState.updatePiece(0,move);
				moves.add(move);
				findOptimalMoves(i+1,diceThrow,dummyGameState,moves,max,rolls + extraRolls);
				moves.remove(i);
			}
		}
		
	}

	@Override
	protected double calculateScore(GameState gameState, int extraRoll) {
		double score = 0.0;

		//for extra roll we get
		if(extraRoll > 0) {
			score += 20;
			extraRoll = 0;
		}
		
		Integer pieces [] [] = gameState.getPieces();
		
		//our pieces evaluation
		for(int j=0; j<4; ++j) {
			//our piece at star
			if(pieces[0][j] != -1 && gameState.getBoard()[pieces[0][j]].getIsStar())
				score += 40;
			//our piece is on run
			if(pieces[0][j] != -1) {
				score += 60;
				for(int k=0; k<4; ++k) {
					if(pieces[0][j] > 100)
						continue;
					if(pieces[1][k] == -1 || pieces[1][k] > 100)
						continue;
					int ourGoalDistance = goalDistance(0,pieces[0][j]);
					int oppGoalDistance = goalDistance(1,pieces[1][k]);
					
					if(ourGoalDistance <= 26 && oppGoalDistance <= 26)
						continue;
					else if (ourGoalDistance > 26 && oppGoalDistance > 26) {
						if(ourGoalDistance > oppGoalDistance)
							ourGoalDistance -= 26;
						else
							oppGoalDistance -= 26;
					}
					
					else if(ourGoalDistance > 26)
						ourGoalDistance -= 26;
					else
						oppGoalDistance -= 26;
					
					double captureProb = weCapture[ourGoalDistance][oppGoalDistance]*100;
					double sacrificeProb = oppCapture[ourGoalDistance][oppGoalDistance]*200;
					score += captureProb + sacrificeProb + pieces[0][j];
				}
			}	
		}
		
		for(int k=0; k<4; ++k)
			if(pieces[1][k] == -1)
				score += 1000;
		
		
		return score;
	}

	private int goalDistance(Integer pid, Integer pieceLoc) {
		if (pid == 0) {
			return 52 - pieceLoc;
		}
		else {
			if (pieceLoc >= 0 && pieceLoc < 26) return 26 - pieceLoc;
			else return 52 - (pieceLoc - 26);
		}
	}

}
