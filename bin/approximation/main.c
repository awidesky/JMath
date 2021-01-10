#include <stdio.h>
#include <math.h>



double func(double y, double x){

 //double a=0.6644388981710444470514099410686507712294;
 //return (a*x)-y;

  return y*(x-3.141592653589/2)*(x-3.141592653589/2) + 1;

}

int main(void) {

  double epsillon = 0.000001;
  double y=0;
  double x;
  double maxErr = -1;
  double betterA;
  double ErrOfBetterA = 100;


  for(y=-0.5; y <= 0.4; y += epsillon){

    double maxErr = -1;
    
    for(x=0.0; x<=3.141592653589/2; x += epsillon){

      double err = fabs(sin(x)-func(y,x));

      maxErr = (err > maxErr) ? err : maxErr;
      
    }

    if(maxErr < ErrOfBetterA) {

      betterA = y;
      ErrOfBetterA = maxErr;

    }

    printf("%lf\n",y);
    //if(fmod(y , 0.1)<0.000001) {printf("\ndone y cycle");}

  }

  printf("Best A = %lf,\nError : %lf\n", betterA, ErrOfBetterA);
  return 0;
}



/*
Best A = 0.072000,
Error : 0.115169

Best A = 0.071400,
Error : 0.115121

Best A = 0.071410,
Error : 0.115111

*/