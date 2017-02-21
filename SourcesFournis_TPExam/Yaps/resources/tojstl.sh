for f in `find . -name "*.jstl" ` ;
do
  d=`dirname $f`
  b=`basename $f .jstl`
  cp -p $f $d/$b;
done
