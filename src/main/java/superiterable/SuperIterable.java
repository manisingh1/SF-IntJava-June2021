package superiterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> target) {
    self = target;
  }

  public SuperIterable<E> filter(/*SuperIterable<E> this,*/ Predicate<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : this.self) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return new SuperIterable<>(res);
  }

  // a "bucket" with this type of map operation is typically called "Functor"
  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
    for (E e : this.self) {
      res.add(op.apply(e));
    }
    return new SuperIterable<>(res);
  }

  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> res = new ArrayList<>();
    for (E e : this.self) {
      for (F f : op.apply(e)) {
        res.add(f);
      }
    }
    return new SuperIterable<>(res);
  }

  public void forEvery(Consumer<E> op) {
    for (E e : self) {
      op.accept(e);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }

}
