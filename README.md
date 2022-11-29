# Blockchain simulation. Part 5: Architecture and SOLID

![image](https://user-images.githubusercontent.com/92053176/192138851-6466d959-734a-4a0c-ab41-3c604095add6.png)

This project implements a simple analogue of a blockchain system that can deal with transactions, accounts and their balances.

In order to run this project, complete the following steps:

- `git clone git@github.com:MaxCojocari/OOP_Lab_6.git`
- `cd OOP_Lab_6/BlockchainProject`
- `javac -cp src -d out src/**/*.java`
- `java -cp out Simulation`

## Performing simulations


Running `java -cp out Simulation` will output a text log with results of simulation:

```
Balances before
0x674707038675dbbf6c374e51aa392a71b33c2175
89653.0 ETH
41628.0 USDT
0xc34af948e20d9b3e19fe2fbb7fc377c7a8ba3127
36351.0 ETH
98651.0 USDT
0x88b0760d27f8141bf27bcee3d1f3185ef524f8c2
28101.0 ETH
97839.0 USDT
0xeb41b1907f2dba8a9046cb34213c320d597cbba5
12489.0 ETH
64885.0 USDT
0xec826fdf3c6aecca4b4269a44ea576d83303cfb3
18472.0 ETH
74319.0 USDT
0xee8a452af235537ebe9cd340418c71c3e12e8c8a
83641.0 ETH
8409.0 USDT
0x5a4753c233c0a52e357671c4edb875e218c79561
54502.0 ETH
51577.0 USDT
0x2db1f68d438983a6b799dabcc49afa756db5b4da
96821.0 ETH
85159.0 USDT
0x5a0692b5e71bd49aea1f0ec712e5653017e2b39d
97067.0 ETH
99050.0 USDT
0x842cf95578871bda37011e954f22bc89de847222
99456.0 ETH
46548.0 USDT


Amount ETH out: 8351.5
SwapTx
Sender:         0x5a4753c233c0a52e357671c4edb875e218c79561
Receiver:       0x4ce82ff5f2f9e7fd1ea5615b42f75aad6b27303d
AmountIn:       16703.0 USDT
AmountOut:      8351.5 ETH

------------------------------------------------------
Amount ETH out: 5543.0
SwapTx
Sender:         0x674707038675dbbf6c374e51aa392a71b33c2175
Receiver:       0x3944d188cfe38b2559ead1f04232326b2aabe04a
AmountIn:       11086.0 USDT
AmountOut:      5543.0 ETH

------------------------------------------------------
TransferTx
Sender:         0xeb41b1907f2dba8a9046cb34213c320d597cbba5
Receiver:       0x674707038675dbbf6c374e51aa392a71b33c2175
Amount:         37869.0 USDT

------------------------------------------------------
Amount to be locked 9210 USDT
Time spent: 9 ms
LockVaultTx
Sender:         0x842cf95578871bda37011e954f22bc89de847222
Receiver:       0x9d7e3b52fdaa68f7a09e6fe54de3dba0133157e6
Amount:         9210 USDT

Amount USDT out (+ interest rate): 9214.605
------------------------------------------------------
Amount USDT out: 7666.1
SwapTx
Sender:         0xee8a452af235537ebe9cd340418c71c3e12e8c8a
Receiver:       0x9d7e3b52fdaa68f7a09e6fe54de3dba0133157e6
AmountIn:       11794.0 ETH
AmountOut:      7666.1 USDT

------------------------------------------------------
Amount to be locked 2760 USDT
Time spent: 13 ms
LockVaultTx
Sender:         0x88b0760d27f8141bf27bcee3d1f3185ef524f8c2
Receiver:       0x3944d188cfe38b2559ead1f04232326b2aabe04a
Amount:         2760 USDT

Amount USDT out (+ interest rate): 2761.3799999999997
------------------------------------------------------
Block # 0
PreviousHash:   null
Timestamp:      1667158823925
Nonce:          6
CurrentHash:    00f6016165c62f3b69222b2288b4c024b948d77101c44d4c45b00036080e18f6
Merkle root:    0ba9d5d106fa803e0cec78d606684cc2a3e02f1627059e2aa1572d42554cb223

Block # 1
PreviousHash:   00f6016165c62f3b69222b2288b4c024b948d77101c44d4c45b00036080e18f6
Timestamp:      1667158823972
Nonce:          371
CurrentHash:    00a6fbcf7c6ccff9d4c96667f7ef8d4478e8cd9c514e383a97099322046f464c
Merkle root:    ae8e5a653d44788bc66df34332486c058a12fa62bcc157c6245815c2b15b73bd

Block # 2
PreviousHash:   00a6fbcf7c6ccff9d4c96667f7ef8d4478e8cd9c514e383a97099322046f464c
Timestamp:      1667158824012
Nonce:          73
CurrentHash:    00ebb34f19d2af0f66f1f0535e8198b3d53f0b46adfdd1338a6ca8051269d5f6
Merkle root:    fab67a7c0cba2438a7a78dd68fdb8a70981e84bcdc292c781869706368549e75

Balances after
0x674707038675dbbf6c374e51aa392a71b33c2175
95196.0 ETH
68411.0 USDT
0xc34af948e20d9b3e19fe2fbb7fc377c7a8ba3127
36351.0 ETH
98651.0 USDT
0x88b0760d27f8141bf27bcee3d1f3185ef524f8c2
28101.0 ETH
97840.38 USDT
0xeb41b1907f2dba8a9046cb34213c320d597cbba5
12489.0 ETH
27016.0 USDT
0xec826fdf3c6aecca4b4269a44ea576d83303cfb3
18472.0 ETH
74319.0 USDT
0xee8a452af235537ebe9cd340418c71c3e12e8c8a
71847.0 ETH
16075.1 USDT
0x5a4753c233c0a52e357671c4edb875e218c79561
62853.5 ETH
34874.0 USDT
0x2db1f68d438983a6b799dabcc49afa756db5b4da
96821.0 ETH
85159.0 USDT
0x5a0692b5e71bd49aea1f0ec712e5653017e2b39d
97067.0 ETH
99050.0 USDT
0x842cf95578871bda37011e954f22bc89de847222
99456.0 ETH
46552.604999999996 USDT
```

Everytime when running a new simulation either with same parameters, or with other ones, it will generate a new set of results which can be compared with batch of data. All entities, their addresses and initial balances are generated randomly, ensuring the diversity of outcomes.

## Architectural patterns

This system is modeled based on MVC architecture and its entities are structured according to the separation of concerns.  

![image](https://user-images.githubusercontent.com/92053176/204489306-40dce966-698c-494b-ab50-716275534297.png)

The View is in charge of the presentation layer (GUI), and it can be anything related to UI, such as buttons, windows, tables, lists, canvas, or any abstraction related to the elements on the screen that the user can see and interact with. In this case, the View is stateless. It is simply rendered by the Controller once the Model is suffering some changes.

The Controller is regarded as the glue or mediator between the Model and the View, and in general
is responsible for altering the Model by reacting to user actions performed on the View and updating the
View with Model changes. The user’s interactions call methods on the Controller which, as a result, updates
the Model internal state.

The Model is responsible for the domain data or a data access layer which manipulates the data
and keeps the business logic. It exposes change listeners to notify observers that it has changed and View
subscribes for changes emitted by the model to update itself.
