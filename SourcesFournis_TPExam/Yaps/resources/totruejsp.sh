for f in `find . -name "*.true" ` ;
do
  d=`dirname $f`
  b=`basename $f .true`
  cp -p $f $d/$b;
done
