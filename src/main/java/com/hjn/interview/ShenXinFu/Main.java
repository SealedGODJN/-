package com.hjn.interview.ShenXinFu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int zero_times = 0; // 记录移动0的次数
        int[][] zero_move_direction_x_y = new int[N][2];

        int x = 0;
        int y = 0;

        // 记录方向的次数
        int x__ = 0;
        int x_ = 0;
        int y__ = 0;
        int y_ = 0;

        int pre_x__ = -1;
        int pre_x_ = -1;
        int pre_y__ = -1;
        int pre_y_ = -1;
        for(int i=0; i<N; i++) {
            int F,L;
            F = sc.nextInt();
            L = sc.nextInt();
            if(F == -1 && L != 0) {
                x -= L;
                if(x < 0) {
                    System.out.println("No");
                    return;
                }
            } else if(F == 1 && L != 0) {
                x += L;
                if(x > 18) {
                    System.out.println("No");
                    return;
                }
            } else if(F == -2 && L != 0) {
                y -=L;
                if(y < 0) {
                    System.out.println("No");
                    return;
                }
            } else if(F == 2 && L != 0) {
                y +=L;
                if(y > 18) {
                    System.out.println("No");
                    return;
                }
            } else if(L==0) {
                zero_move_direction_x_y[zero_times][0] = F;

                if(F == -1) {
                    x__++;
                    x--;
                    if(x==0) {
                        if(pre_x_ == -1) {
                            System.out.println("No");
                            return;
                        } else if(pre_x_ != -1 && (zero_move_direction_x_y[pre_x_][1]+1) > 18) {
                            System.out.println("No");
                            return;
                        }
                        x_--;
                    }
                    x++;
                    zero_move_direction_x_y[zero_times][1] = x;
                    pre_x__ = zero_times;
                } else if(F == 1) {
                    x_++;
                    x++;
                    if(x==18) {
                        if(pre_x__ == -1) {
                            System.out.println("No");
                            return;
                        } else if(pre_x__ != -1 && (zero_move_direction_x_y[pre_x_][1]-1) < 0) {
                            System.out.println("No");
                            return;
                        }
                        x__--;
                    }
                    x--;
                    zero_move_direction_x_y[zero_times][1] = x;
                    pre_x_ = zero_times;
                } else if(F == -2) {
                    y__++;
                    y--;
                    if(y==0) {
                        if(pre_y_ == -1) {
                            System.out.println("No");
                            return;
                        } else if(pre_y_ != -1 && (zero_move_direction_x_y[pre_x_][1]+1) > 18) {
                            System.out.println("No");
                            return;
                        }
                        y_--;
                    }
                    y++;
                    zero_move_direction_x_y[zero_times][1] = y;
                    pre_y__ = zero_times;
                } else if(F == 2) {
                    y_++;
                    y++;
                    if(y==18) {
                        if(pre_x__ == -1) {
                            System.out.println("No");
                            return;
                        } else if(pre_x__ != -1 && (zero_move_direction_x_y[pre_x_][1]-1) < 0) {
                            System.out.println("No");
                            return;
                        }
                        y__--;
                    }
                    y--;
                    zero_move_direction_x_y[zero_times][1] = y;
                    pre_y_ = zero_times;
                }

                zero_times++;
            }
        }
        if( x_ > 0) {
//            if( (x+x_-x__) <= 18 ) x = 18;
            if( x__ != 0) {
                if( x> x_) {
                    y = 18;
                }
            } else if( x+x_ <= 18) {
                x = 18;
            }
        }
        if( y_ > 0) {
//            if( (y+y_-y__) <= 18 ) y = 18;
            if( y__ != 0) {
                if( y> y_) {
                    y = 18;
                }
            } else if( y+y_ <= 18) {
                y = 18;
            }
        }


//        for(int i=0; i<=zero_times; i++) {
//            int direction = zero_move_direction_x_y[i][0];
//            if(direction == -1) {
//                pre_x__ = i; // 记录位置
//            } else if(direction == 1) {
//                pre_x_ = i;
//            }
//        }
        if(x == 18 && y == 18) {
            System.out.println("Yes");
        }
    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int T = sc.nextInt();
//        for(int i=0; i<T; i++) {
//            int N = sc.nextInt();
//            long[][] nums = new long[5][N];
//            for(int j=0; j<5; j++) {
//                for(int k=0; k<N; k++) {
//                    nums[j][k] = sc.nextLong();
//                }
//            }
//            int[] index = {0,0,0,0,0};
//            long sum = 0;
//            int loc = 0; // 记录修改的位置
//            int times = 0; // 记录修改的次数
//            while(true) {
//                for(int j=0; j<5; j++) {
//                    sum += nums[j][index[j]];
//                }
//                if(sum == 2018) {
//                    System.out.println("Yes");
//                    break;
//                }
//                index[loc]++;
//                times++;
//                if(times == N - 1) {
////                    index[loc] = 0;
//                    loc++;
//                    times=0;
//                }
//                if(loc == 5) {
//                    break;
//                }
//            }
//            loc = 4;
//            while(true) {
//                for(int j=0; j<5; j++) {
//                    sum += nums[j][index[j]];
//                }
//                if(sum == 2018) {
//                    System.out.println("Yes");
//                    break;
//                }
//                index[loc]++;
//                times++;
//                if(times == N - 1) {
////                    index[loc] = 0;
//                    loc--;
//                    times=0;
//                }
//                if(loc == -1) {
//                    break;
//                }
//            }
//            System.out.println("No");
//        }
//    }
}
