run:
  java src/app/app.java

view:
  java src/app/app.java | tee /dev/tty | rg write | cut -d ' ' -f 2 | xargs chafa

imv:
  java src/app/app.java | tee /dev/tty | rg write | cut -d ' ' -f 2 | xargs imv
