run:
  java src/app/app.java

view:
  chafa "$(java src/app/app.java | rg write | cut -d ' ' -f 2)"

imv:
  java src/app/app.java | rg write | cut -d ' ' -f 2 | imv
