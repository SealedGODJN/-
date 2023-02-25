class Test:
    def __init__(self):
        self.i = 1
        print("init!")
 
    def modify(self):
        self.i+=1
 
    def do(self):
        print(self.i)