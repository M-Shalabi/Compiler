```
git clone https://github.com/DevMedo/Compiler
```

<H1> SLR Parser In Java </H1>
<p> SLR parser is quite efficient at finding the single correct bottom-up parse in a single left-to-right scan over the input stream, without guesswork or backtracking. The parser is mechanically generated from a formal grammar for the language. </p>
<details><a href=https://en.wikipedia.org/wiki/Simple_LR_parser>Source: Simple LR parser
From Wikipedia</a></details>

<h2> <ins>1.Input.txt</ins> </h2>
<q>(id+id)*id$</q>
<br>
<p> Note: You can also use <q>(id+id)*(id+id)$</q></p>

<h2>2.Rules </h2>
<ol>
  <li>E → E+T</li>
  <li>E → T</li>
  <li>T → T*F</li>
  <li>T → F</li>
  <li>F → (E)</li>
  <li>F → id</li>
</ol>

<h2>3.Parse Table </h2>
<img src="https://github.com/DevMedo/College/blob/master/SLR%20Parser%20Java/ParseTable.gif"; height="500px"; width="500px";>
<h2>4.Output </h2>
<img src="https://github.com/DevMedo/Compiler/blob/master/SLR%20Parser%20Java/output.png">
<h2>5.Example </h2>
<img src="https://github.com/DevMedo/Compiler/blob/master/SLR%20Parser%20Java/1.jpg";  height="500px"; width="500px";>
<img src="https://github.com/DevMedo/Compiler/blob/master/SLR%20Parser%20Java/2.jpg";  height="500px"; width="500px";>
