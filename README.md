# Scramblies Challenge - Web app

## Running the server

Figwheel can be run with from the command line with...

    clj -A:dev
    
You can then browse the scramble web app at http://localhost:9500

## Comments/assumptions

I have seen in Flexiana's web page that you like using `duct` as a framework for web development. I haven't done ClojureScript before, so I have opted for a basic set up with `figwheel`, and I have done dynamic HTML with `reagent`, as it seems a popular choice.

The page is plain. I haven't payed attention to aesthetics, as I understand that we are not exercising that aspect.

I haven't invested in a production-oriented set up, nor in further libraries for data binding or other aspects, which could be an option for bigger apps. In a real case when working for Flexiana, I would adapt and collaborate to Flexiana's practices.
