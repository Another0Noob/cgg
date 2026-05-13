run:
  java src/app/app.java

view:
  chafa "$(java src/app/app.java | cut -d ' ' -f 2)"
