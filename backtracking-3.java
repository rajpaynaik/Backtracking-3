//time complexity O(N!) and space O(N2)
class Solution {
    List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();
        boolean[][] board = new boolean[n][n];
        helper(board,0,n); 

        return result;
        
    }

    private void helper(boolean[][] board, int r, int n){

        //base
        if(r==n){
            List<String> li = new ArrayList<>();
            for(int i=0;i<n;i++){
                
                StringBuilder str = new StringBuilder();
                for(int j=0;j<n;j++){
                    if(board[i][j]){
                        str.append('Q');
                    }else{
                        str.append('.');
                    }
                }
                li.add(str.toString());
            }
            result.add(li);
        }



        //logic
        for(int c=0;c<n;c++){

            if(isSafe(board,r,c,n)){
                //action
                board[r][c]=true;

                //recurse
                helper(board,r+1,n);

                //backtrack
                board[r][c]=false;
            }
        }
    }

    private boolean isSafe(boolean[][] board,int r,int c,int n){

        //column up
        for(int i=0;i<=r;i++){
            if(board[i][c]) return false;
        }

        //digonal right
        int i=r;
        int j=c;
        while(i>=0 && j<n){
            if(board[i][j]) return false;
            i--;
            j++;
        }

        //digonal left
        i=r;
        j=c;
        while(i>=0 && j>=0){
            if(board[i][j])return false;
            i--;
            j--;
        }

        return true;
    }
}

//time complexity (N 3^L) and space complexity O(L)
class Solution {
    boolean flag= false;
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!flag){
                    backtrack(board,word,i,j,0);
                }
            }
        }

        return flag;
        
    }

    public void backtrack(char[][] board, String word, int i, int j,int idx){
        //base
        if(idx==word.length()){
            flag=true;
            return;
        }

        if(i<0 || j<0 || i==m || j==n || board[i][j]=='#'){
            return;
        }


        //logic
        if(board[i][j]==word.charAt(idx)){
            board[i][j]='#';
            for(int[] dir:dirs){
                int nr = dir[0]+i;
                int nc = dir[1]+j;
                if(!flag){
                    backtrack(board,word,nr,nc,idx+1);
                }
                if(flag){
                    break;
                }
            }
            board[i][j]=word.charAt(idx);
        }
    }
}
