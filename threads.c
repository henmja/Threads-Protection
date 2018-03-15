#include <stdio.h>
#include<string.h>
#include <pthread.h>
#include<stdlib.h>
#include<unistd.h>

typedef unsigned char mutex;
#define MUTEX_FREE 0
#define MUTEX_BUSY 1

char **global_argv;
int G = 0;
pthread_t tid[3];
//pthread_mutex_t lock;

mutex testAndSet(mutex *m) {
        int result;
        asm ("lock bts $0, %1; sbb %0, %0"
        :"=r" (result)
        :"m" (*m)

        :"memory");

  return (result & 1);

                }


  void P(mutex *m) {

         while(testAndSet(m) == MUTEX_BUSY)

         usleep(10);

         }


  void V(mutex *m) {

         *m = MUTEX_FREE;

         }

 mutex m = MUTEX_FREE;

                                                
void* incOne(void *arg) {                                                                                                      
	P(&m);
	int j = 0;
	while(j<atoi(global_argv[3])) {

        int a = G;


	G = a+1;
	printf("G value: %d \n",G);  
	j++;
 }

        V(&m);

//                              pthread_mutex_unlock (&lock);
return           NULL;         
}
void* incOneWithoutProt(void *arg)
{

int j = 0;
                                                              
while(j<atoi(global_argv[3])) {

int a = G;
G = a+1;
printf("G value: %d \n",G);
j++;
 }

V(&m);

//                              pthread_mutex_unlock (&lock);
return NULL;         
}
void* incOneWithoutProt(void *arg) {
int j = 0;

while(j<atoi(global_argv[3])) {
        int a = G;
        G=a+1;
        j++;
        printf("G Value: %d \n",G);
}

}

int main(int argc, char* argv[])
{

        global_argv = argv;
        int i = 0;
        int err;


if (argc==4) {
if (strcmp(argv[1],"--with-protection")==0) {
while(i<atoi(argv[2])) {
err = pthread_create(&(tid[i]), NULL, &incOne, NULL);

if (err != 0)
printf("\ncan't create thread :[%s]", strerror(err));
else
        printf("\n Thread created successfully\n");
i++;
}
int c = 0;
while (c<atoi(argv[2])) {
pthread_join(tid[c], NULL);
c++;
}
printf("%d\n",G);
int v = (atoi(argv[3])*atoi(argv[2]))-G;
if (G==atoi(argv[3])*atoi(argv[2])) {
        printf("OK!\n Number of violations: 0\n");
} else {
        printf("Fail!\n Number of violations: %d \n", v);
}

sleep(5);
} else if (strcmp(argv[1],"--without-protection")==0) {
int j = 0;
int err;
while(j<atoi(argv[2])){

        err = pthread_create(&(tid[j]), NULL, &incOneWithoutProt, NULL);
        if (err != 0)
                printf("\ncan't create thread :[%s]", strerror(err));
        else
                printf("\n Thread created successfully\n");


                j++;


}
int d = 0;
while (d<atoi(argv[2])) {
pthread_join(tid[d], NULL);
d++;
}
printf("%d\n",G);
int v = (atoi(argv[3])*atoi(argv[2]))-G;
if (G!=atoi(argv[3])*atoi(argv[2])) {
        printf("Fail!\nNumber of violations: %d \n", v);
} else {
        printf("OK!\n Number of violations: 0\n");
}
sleep(5);
return 0;
}       else {
        printf("invalid argument(s)\n");
}
} else {
        printf("insufficient valid arguments\n");
}
return 0;
}
