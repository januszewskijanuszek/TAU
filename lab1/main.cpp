#include <iostream>
#include <vector>
#include "calc.h"
#include <ctime>
#include <unistd.h>

#define TEST_AMOUNT 10

using namespace std;

double fRand(double fMin, double fMax){
    double f = (double)rand() / RAND_MAX;
    return fMin + f * (fMax - fMin);
}

string generateRandomString(const int len){
    static const char alphanum[] =
            "0123456789"
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            "abcdefghijklmnopqrstuvwxyz";
    string tmp_s;
    tmp_s.reserve(len);
    for (int i = 0; i < len; ++i) {
        tmp_s += alphanum[rand() % (sizeof(alphanum) - 1)];
    }

    return tmp_s;
}

void printStatment(pair<double, double> par, string input){
    cout << par.first << " - num 1 | " << par.second << " - num 2" << endl;
    cout << input << " - ";
    calc(input, {par.first, par.second});
}

int main(){
    srand((unsigned)time(NULL) * getpid());
    vector<string> correct = {"add", "mod", "sin"};
    for (int i = 0; i < TEST_AMOUNT; ++i) {
        if(i < 5){
            string input = generateRandomString(10);
            pair<double, double> pair1 = make_pair(fRand(0, 1000), fRand(0, 1000));
            printStatment(pair1, input);
        }
        else{
            string input = correct.at(fRand(0,correct.size()));
            pair<double, double> pair1 = make_pair(fRand(0, 1000), fRand(0, 1000));
            printStatment(pair1, input);
        }
    }
    return 0;
}
