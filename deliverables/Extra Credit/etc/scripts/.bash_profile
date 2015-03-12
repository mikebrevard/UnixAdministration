# .bash_profile

# Get the aliases and functions
if [ -f ~/.bashrc ]; then
  . ~/.bashrc
fi

# User specific environment and startup programs

JAVA_HOME=/usr/java/jdk1.7.0_75
export JAVA_HOME
PATH=$HOME:$JAVA_HOME/bin:$PATH
export PATH
