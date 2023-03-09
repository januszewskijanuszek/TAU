#include <iostream>
#include <map>
#include <functional>
#include <vector>
#include <string>
#include <cmath>
#include "calc.h"

#define MESSAGE "Select one from 3: add, mod lub sin."

using namespace std;
using myFunction = function<double(vector<double>)>;

void printValues(vector<double> numb, myFunction fun){
    cout << fun(numb) << endl;
}

int calc(string func, vector<double> variables) {
    map<string, myFunction> options;
    options["add"] = [](vector<double> numbers) { return numbers.front() + numbers.back(); };
    options["mod"] = [](vector<double> numbers) { return (int)numbers.front() % (int)numbers.back(); };
    options["sin"] = [](vector<double>numbers) { return sin(numbers.front()); };
    try {
        printValues(variables, options.at(func));
    }
    catch (out_of_range ofr){
        cout << MESSAGE << endl;
    } catch (bad_function_call bfc) {
        cout << MESSAGE << endl;
    }
    return 0;
}