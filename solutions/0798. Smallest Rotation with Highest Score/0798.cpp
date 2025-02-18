class Solution {
 public:
  int bestRotation(vector<int>& A) {
    const int n = A.size();
    // rotate[i] := how many points losing after rotating left i times
    vector<int> rotate(n);

    // Rotating i - A[i] times makes A[i] == its new index
    // So rotating i - A[i] + 1 times will "start" to make A[i] > its index,
    // Which is the starting index to lose point
    for (int i = 0; i < n; ++i)
      --rotate[(i - A[i] + 1 + n) % n];

    // Each time we rotate, we make index 0 to index n - 1,
    // So we get 1 point
    for (int i = 1; i < n; ++i)
      rotate[i] += rotate[i - 1] + 1;

    return distance(begin(rotate), max_element(begin(rotate), end(rotate)));
  }
};
